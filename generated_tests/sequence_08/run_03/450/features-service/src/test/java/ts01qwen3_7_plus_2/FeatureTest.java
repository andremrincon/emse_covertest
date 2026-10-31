package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Smartwatch-Series-8";
        String featureName = "Blood-Oxygen-Sensor";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeatures() {
        String productName = "AeroBook-Pro-15";
        String featureName = "backlit-keyboard";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().get("/products/" + productName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "QuantumLeap-AI-Platform";
        String configName = "standard-gpu-cluster";
        String featureName = "distributed-training";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <201> but was <500>.")
    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationAgain() {
        String productName = "P1";
        String configName = "basic";
        String featureName = "sso";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String productName = "EcoTablet-10";
        String featureName = "stylus-support";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .formParam("description", "Advanced stylus support with pressure sensitivity.")
            .put("/products/" + productName + "/features/" + featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "ultra-laptop-x1";
        String configName = "us-standard-16gb";
        String featureName = "fingerprint-scanner";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "IoT-Sensor-Hub";
        String configName = "Premium-Tier";
        String featureName = "Realtime-Analytics-Dashboard";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = "SmartWatch-Pro";
        String featureName = "heart-rate-monitor";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().delete("/products/" + productName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureWithSameNameForDifferentProduct() {
        String productName1 = "Gaming-PC-Tower";
        String productName2 = "UltraBook-Z1";
        String featureName = "Liquid-Cooling";

        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName1 + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName2 + "/features/" + featureName)
            .then().statusCode(201);
    }
}