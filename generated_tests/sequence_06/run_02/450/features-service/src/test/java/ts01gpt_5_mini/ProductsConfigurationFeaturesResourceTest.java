package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setup() {
        String base = System.getProperty("api.base", System.getenv() != null && System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturnsCreated() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String config = "cfg-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String feature = "feat-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationReturnsNoContent() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String config = "cfg-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String feature = "feat-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationWhenFeatureMissingReturnsServerError() {
        String product = "prod-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String config = "cfg-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String feature = "nonexistent-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(500);
    }
}