package ts01qwen3_7_plus;

import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Success() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String configurationName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feature_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .post(baseUrl + "/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Failure() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String configurationName = "Config_" + UUID.randomUUID().toString();
        String featureName = "NonExistentFeature_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
                .when()
                .post(baseUrl + "/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String configurationName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feature_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .delete(baseUrl + "/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String configurationName = "Config_" + UUID.randomUUID().toString();
        String featureName = "NonExistentFeature_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
                .when()
                .delete(baseUrl + "/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }
}