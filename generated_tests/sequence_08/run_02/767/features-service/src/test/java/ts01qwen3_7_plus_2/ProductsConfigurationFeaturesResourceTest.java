package ts01qwen3_7_plus_2;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "Product1_" + UUID.randomUUID().toString();
        String configName = "Config1_" + UUID.randomUUID().toString();
        String featureName = "Feature1_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String productName = "Product2_" + UUID.randomUUID().toString();
        String configName = "Config2_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureA").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatureB").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/FeatureA").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/FeatureB").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatureA").formParam("requiredFeature", "FeatureB").when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/FeatureB").then().statusCode(204);
    }
}