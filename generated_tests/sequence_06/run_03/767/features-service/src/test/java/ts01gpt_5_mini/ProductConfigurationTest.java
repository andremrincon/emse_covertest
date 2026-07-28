package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationWhenProductHasFeatures() {
        String productName = "prod-with-features-" + UUID.randomUUID().toString();
        String featureA = "Feature-A-" + UUID.randomUUID().toString();
        String featureB = "Feature-B-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));

        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationWhenProductHasNoFeatures() {
        String productName = "prod-no-features-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationActivatesFeature() {
        String productName = "prod-activate-" + UUID.randomUUID().toString();
        String featureName = "feature-to-activate-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        resp.then().statusCode(201);
    }
}