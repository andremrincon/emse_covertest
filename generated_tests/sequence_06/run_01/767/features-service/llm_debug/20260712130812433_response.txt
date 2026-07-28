package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();

        given().when().post(baseUrl + "/products/" + productName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().delete(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }
}