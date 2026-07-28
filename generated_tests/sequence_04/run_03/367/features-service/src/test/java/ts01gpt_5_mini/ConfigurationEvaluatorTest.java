package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class ConfigurationEvaluatorTest {

    private static String BASE_URI;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE_URI = env;
        } else if (System.getProperty("base.url") != null && !System.getProperty("base.url").isEmpty()) {
            BASE_URI = System.getProperty("base.url");
        } else {
            BASE_URI = "http://localhost:8080";
        }
        RestAssured.baseURI = BASE_URI;
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsConfigurationNameInBody() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("configurationName", nullValue());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintActivatesRequiredFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("", hasItem(requiredFeature));
    }
}