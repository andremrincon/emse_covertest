package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_Empty() {
        String productName = "prod-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().pathParam("productName", productName).when().get("/products/{productName}/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_WithConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", productName).when().get("/products/{productName}/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_Empty() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("configurationName", configName).when().get("/products/{productName}/configurations/{configurationName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_WithFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("configurationName", configName).when().get("/products/{productName}/configurations/{configurationName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Success() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Duplicate() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_Success() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(204);
    }
}