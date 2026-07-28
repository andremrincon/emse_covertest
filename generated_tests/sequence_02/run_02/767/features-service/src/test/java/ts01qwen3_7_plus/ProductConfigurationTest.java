package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class ProductConfigurationTest {

    @Test(timeout = 60000)
    public void testCreateProduct() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateConfiguration() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testActivateFeature() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeactivateFeature() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().delete("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesOne() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().get("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesZero() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().get("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetails() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().get("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActivateMultipleFeatures() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName1 = "Feat1_" + UUID.randomUUID().toString();
        String featureName2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName2).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesMultiple() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName1 = "Feat1_" + UUID.randomUUID().toString();
        String featureName2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().get("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeactivateOneOfMultipleFeatures() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName1 = "Feat1_" + UUID.randomUUID().toString();
        String featureName2 = "Feat2_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().delete("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName1).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testActivateAlreadyActiveFeature() {
        String productName = "Prod_" + UUID.randomUUID().toString();
        String featureName = "Feat_" + UUID.randomUUID().toString();
        String configName = "Conf_" + UUID.randomUUID().toString();
        given().when().post("http://localhost:8080/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("http://localhost:8080/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500);
    }
}