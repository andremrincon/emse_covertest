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
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssured.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"));
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesTriggersEvaluationResultConstruction() {
        String productName = "EvalTest-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithRequiresConstraintTriggersEvaluationResult() {
        String productName = "EvalReq-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationDetailsTriggersEvaluationResultConstruction() {
        String productName = "EvalDet-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "det-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "detcfg-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName).then().statusCode(200);
    }
}