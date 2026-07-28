package ts01qwen3_7_plus;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_NoConfigurations() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/products/" + productName + "/configurations");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_WithConfigurations() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/products/" + productName + "/configurations");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_NoFeatures() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_WithFeatures() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Success() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Duplicate() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_Success() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Config_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        given().when().post(BASE_URL + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().delete(BASE_URL + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        response.then().statusCode(204);
    }
}