package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    static {
        if (System.getenv("BASE_URL") != null) {
            RestAssured.baseURI = System.getenv("BASE_URL");
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "QuantumLeap-AI-" + UUID.randomUUID().toString();
        String configurationName = "Premium-Tier-" + UUID.randomUUID().toString();
        String featureName = "Realtime-Analytics-Dashboard-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String productName = "DataStreamer-" + UUID.randomUUID().toString();
        String configurationName = "Standard-" + UUID.randomUUID().toString();
        String featureName = "Push-Notifications-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }
}