package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetails() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActivateUnavailableFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String unavailableFeature = "Unavailable-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + unavailableFeature).then().statusCode(500);
    }
}