package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String productName = "Product_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feature_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String productName = "Product_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feature_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(500);
    }
}