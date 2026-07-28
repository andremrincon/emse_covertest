package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.config.DecoderConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssured.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void getConfigurationTriggersEvaluationResultConstruction_1() {
        String productName = "eval-product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "eval-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "eval-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getActiveFeaturesTriggersEvaluationResultConstruction_1() {
        String productName = "eval-product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "eval-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "eval-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithConstraintsTriggersEvaluationResultConstruction_1() {
        String productName = "eval-product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "eval-config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(200);
    }
}