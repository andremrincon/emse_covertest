package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationActivatesFeature() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesIncludesAddedFeature() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName);
        assertTrue(resp.getBody().asString().contains(featureName));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationDeactivatesFeature() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureFalseWhenNotActive() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName);
        assertFalse(resp.getBody().asString().contains(featureName));
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaProductFeaturesEndpoint() {
        String productName = "prod-" + UUID.randomUUID();
        String featureA = "featA-" + UUID.randomUUID();
        String featureB = "featB-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsValidTrue() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        Boolean valid = resp.jsonPath().getBoolean("valid");
        assertTrue(valid);
    }

    @Test(timeout = 60000)
    public void testAddProductFeatureCreatesFeature() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductFeatureRemovesFeature() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, resp.getStatusCode());
    }
}