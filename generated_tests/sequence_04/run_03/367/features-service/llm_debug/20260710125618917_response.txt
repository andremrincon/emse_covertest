package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaGetConfiguration() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaGetConfigurations() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaAddFeatureToConfiguration() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
    }
}