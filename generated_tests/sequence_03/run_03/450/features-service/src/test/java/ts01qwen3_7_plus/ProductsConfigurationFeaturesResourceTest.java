package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFailure() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "NonExistentFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureSuccess() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFailure() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "NonExistentFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500);
    }
}