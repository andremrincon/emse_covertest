package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
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
    public void testAvailableFeaturesListed() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureA).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).when().get("/products/{productName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateProductConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationByName() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesIncludesAddedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().get("/products/{productName}/configurations/{configurationName}/features").then().body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testDeactivateFeatureFromConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureFalseWhenNotAdded() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().get("/products/{productName}/configurations/{configurationName}/features").then().body("$", not(hasItem(featureName)));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetailsIncludesValidFlag() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().get("/products/{productName}/configurations/{configurationName}").then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductEndpointReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().delete("/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProductReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Updated desc").pathParam("productName", productName).pathParam("featureName", featureName).when().put("/products/{productName}/features/{featureName}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConfigurationReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().delete("/products/{productName}/configurations/{configurationName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProductReturnsList() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).when().get("/products/{productName}/configurations").then().statusCode(200);
    }
}