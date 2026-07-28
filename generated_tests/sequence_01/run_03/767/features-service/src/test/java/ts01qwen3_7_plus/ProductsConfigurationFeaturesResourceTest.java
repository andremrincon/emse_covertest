package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String productName = "Prod_Add_Success_" + UUID.randomUUID().toString();
        String configName = "Config_Add_Success_" + UUID.randomUUID().toString();
        String featureName = "Feat_Add_Success_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFailure() {
        String productName = "Prod_Add_Fail_" + UUID.randomUUID().toString();
        String configName = "Config_Add_Fail_" + UUID.randomUUID().toString();
        String featureName = "Feat_Add_Fail_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureSuccess() {
        String productName = "Prod_Del_Success_" + UUID.randomUUID().toString();
        String configName = "Config_Del_Success_" + UUID.randomUUID().toString();
        String featureName = "Feat_Del_Success_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFailure() {
        String productName = "Prod_Del_Fail_" + UUID.randomUUID().toString();
        String configName = "Config_Del_Fail_" + UUID.randomUUID().toString();
        String featureName = "Feat_Del_Fail_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500);
    }
}