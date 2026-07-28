package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void getConfigurationReturnsAvailableFeaturesWhenProductHasFeatures() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationTriggersAvailableFeaturesCheck() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationActiveFeaturesAfterActivatingFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/configurations/{configurationName}/features",
                        productName, configurationName)
                .then().statusCode(200);
    }
}