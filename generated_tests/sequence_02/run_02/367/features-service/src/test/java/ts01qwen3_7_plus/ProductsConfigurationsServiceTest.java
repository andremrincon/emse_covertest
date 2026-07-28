package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_WithExistingConfigurations() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
                .when().get("/products/{productName}/configurations", productName)
                .then().body("$", hasItem(configName));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_WithNoConfigurations() {
        String productName = "Prod-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
                .when().get("/products/{productName}/configurations", productName)
                .then().body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_WithActiveFeatures() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
                .when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then().body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_WithNoActiveFeatures() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
                .when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then().body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_ExistingFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
                .when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_NonExistentFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
                .when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfiguration_NewFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfiguration_AlreadyActiveFeature() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
                .then().statusCode(500);
    }
}