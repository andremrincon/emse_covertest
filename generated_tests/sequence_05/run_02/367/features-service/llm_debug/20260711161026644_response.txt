package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private void arrangeCreateProduct(String productName) {
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
    }

    private void arrangeAddFeatureToProduct(String productName, String featureName) {
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
    }

    private void arrangeCreateConfiguration(String productName, String configurationName) {
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
    }

    private void arrangeAddFeatureToConfiguration(String productName, String configurationName, String featureName) {
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeAddFeatureToProduct(productName, featureName);
        Response resp = given().when().get("/products/{productName}/features", productName);
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testCreateAndGetProduct200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        Response resp = given().when().get("/products/{productName}", productName);
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationHasEmptyActivedFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeCreateConfiguration(productName, configName);
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        resp.then().body("activedFeatures", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeAddFeatureToProduct(productName, featureName);
        arrangeCreateConfiguration(productName, configName);
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertEquals(201, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeAddFeatureToProduct(productName, featureName);
        arrangeCreateConfiguration(productName, configName);
        arrangeAddFeatureToConfiguration(productName, configName, featureName);
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertEquals(204, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesContainsNames() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeAddFeatureToProduct(productName, featureA);
        arrangeAddFeatureToProduct(productName, featureB);
        arrangeCreateConfiguration(productName, configName);
        arrangeAddFeatureToConfiguration(productName, configName, featureA);
        arrangeAddFeatureToConfiguration(productName, configName, featureB);
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName);
        resp.then().body("", hasItems(featureA, featureB));
    }

    @Test(timeout = 60000)
    public void testFeaturePresenceTrueAfterAdd() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeAddFeatureToProduct(productName, featureName);
        arrangeCreateConfiguration(productName, configName);
        arrangeAddFeatureToConfiguration(productName, configName, featureName);
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        resp.then().body("activedFeatures.name", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testFeatureAbsenceWhenNotAdded() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeAddFeatureToProduct(productName, featureName);
        arrangeCreateConfiguration(productName, configName);
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName);
        resp.then().body("", not(hasItem(featureName)));
    }

    @Test(timeout = 60000)
    public void testDeleteNonExistingFeatureReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String nonExistingFeature = "nonexist-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeCreateConfiguration(productName, configName);
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, nonExistingFeature);
        assertEquals(500, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testCreatedConfigurationHasValidTrue() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        arrangeCreateProduct(productName);
        arrangeCreateConfiguration(productName, configName);
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        resp.then().body("valid", equalTo(true));
    }
}