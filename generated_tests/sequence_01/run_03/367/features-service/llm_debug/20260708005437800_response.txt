package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setupClass() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_createsFeature() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures SpO2").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_triggersFeatureEqualityPath() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Distributed training support").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_updatesDescription() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Initial desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "RGB backlit keyboard with customizable zones").when().put("/products/{productName}/features/{featureName}", product, feature).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_removesFeature() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "To be deleted").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_invokesEqualsForRemoval() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Realtime analytics").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String featureA = "featureA-" + UUID.randomUUID();
        String featureB = "featureB-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "A").when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "B").when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Enabled feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(200);
    }
}