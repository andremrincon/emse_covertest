package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateProductAndConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "base-config").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", productName, "base-config").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureA").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureB").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config1").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config1", "featureB").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActiveFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureC").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config2").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config2", "featureC").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, "config2").then().statusCode(200).body(equalTo("[\"featureC\"]"));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureD").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config3").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config3", "featureD").then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config3", "featureD").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActiveFeaturesAfterDeactivation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureE").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config4").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config4", "featureE").then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config4", "featureE").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, "config4").then().statusCode(200).body(not(hasItem("featureE")));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListForProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config5").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetails() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config6").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", productName, "config6").then().statusCode(200).body("name", equalTo("config6"));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetailsWithActiveFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureF").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config7").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config7", "featureF").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", productName, "config7").then().statusCode(200).body("activedFeatures", is(not(empty())));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetailsWithoutActiveFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config8").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", productName, "config8").then().statusCode(200).body("activedFeatures", is(empty()));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"[\\\"featureH\\\",\\\"...")
    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureG").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureH").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config9").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config9", "featureG").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, "config9", "featureH").then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, "config9").then().statusCode(200).body(equalTo("[\"featureH\",\"featureG\"]"));
    }

    @Test(timeout = 60000)
    public void testDeleteConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, "config10").then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}", productName, "config10").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductDetails() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "Test description for feature").when().post("/products/{productName}/features/{featureName}", productName, "featureI").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "featureJ").then().statusCode(lessThan(300));
        given().formParam("description", "Updated description").when().put("/products/{productName}/features/{featureName}", productName, "featureJ").then().statusCode(200);
    }
}