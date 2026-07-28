package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns204WhenFeatureExists() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns400WhenFeatureNotInConfiguration() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromNonExistentConfigurationReturns400() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String configurationName = "nonexistent-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(500);
    }
}