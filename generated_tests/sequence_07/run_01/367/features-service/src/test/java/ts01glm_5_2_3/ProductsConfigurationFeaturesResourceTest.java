package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns204WhenValid() {
        String productName = "QuantumLeap-AI-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "Premium-Tier";
        String featureName = "Realtime-Analytics-Dashboard";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturnsErrorWhenInvalid() {
        String productName = "IoT-Sensor-Hub-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "Basic";
        String featureName = "GPS";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns500WhenProductNotFound() {
        String productName = "NonExistentProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "Standard";
        String featureName = "Push-Notifications";

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then().statusCode(500);
    }
}