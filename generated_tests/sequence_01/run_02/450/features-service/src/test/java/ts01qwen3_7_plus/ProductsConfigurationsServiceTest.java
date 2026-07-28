package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    private String uuid() {
        return java.util.UUID.randomUUID().toString();
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_Empty() {
        String productName = "prod-" + uuid();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));

        given().when().get(baseUrl + "/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_NotEmpty() {
        String productName = "prod-" + uuid();
        String configName = "config-" + uuid();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get(baseUrl + "/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_Empty() {
        String productName = "prod-" + uuid();
        String configName = "config-" + uuid();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_NotEmpty() {
        String productName = "prod-" + uuid();
        String featureName = "feat-" + uuid();
        String configName = "config-" + uuid();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfiguration_Success() {
        String productName = "prod-" + uuid();
        String featureName = "feat-" + uuid();
        String configName = "config-" + uuid();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfiguration_Duplicate() {
        String productName = "prod-" + uuid();
        String featureName = "feat-" + uuid();
        String configName = "config-" + uuid();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_Success() {
        String productName = "prod-" + uuid();
        String featureName = "feat-" + uuid();
        String configName = "config-" + uuid();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }
}