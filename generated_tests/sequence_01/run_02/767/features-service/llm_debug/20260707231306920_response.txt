package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        RestAssured.baseURI = (base != null && !base.isEmpty()) ? base : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddingFeatureToConfigurationTriggersDerivedFeatureEvaluation() {
        String uid = UUID.randomUUID().toString();
        String product = ("prod-" + uid).replaceAll("[^A-Za-z0-9\\-]", "-");
        String featureA = ("featureA-" + uid).replaceAll("[^A-Za-z0-9\\-]", "-");
        String featureB = ("featureB-" + uid).replaceAll("[^A-Za-z0-9\\-]", "-");
        String configuration = ("config-" + uid).replaceAll("[^A-Za-z0-9\\-]", "-");

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("requiredFeature", featureB).when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureA).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddingFeatureToConfigurationWithoutDerivedFeatures() {
        String uid = UUID.randomUUID().toString();
        String product = ("prod2-" + uid).replaceAll("[^A-Za-z0-9\\-]", "-");
        String feature = ("featureSolo-" + uid).replaceAll("[^A-Za-z0-9\\-]", "-");
        String configuration = ("config2-" + uid).replaceAll("[^A-Za-z0-9\\-]", "-");

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(201);
    }
}