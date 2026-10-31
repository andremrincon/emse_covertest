package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            String e = System.getenv("API_BASE_URL");
            if (e == null || e.isEmpty()) {
                e = "http://localhost:8080";
            }
            env = e;
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testCreateProductReturns201() {
        String productName = "Wireless-Product-" + UUID.randomUUID().toString();
        Response act = given().when().post("/products/{productName}", productName);
        assertEquals(201, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "Smartwatch-" + UUID.randomUUID().toString();
        String featureName = "Blood-Oxygen-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures200ContainsAddedFeature() {
        String productName = "AeroBook-" + UUID.randomUUID().toString();
        String featureName = "16GB-LPDDR5-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "High-speed memory for seamless multitasking.").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", productName);
        assertTrue(act.asString().contains(featureName));
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationReturns201() {
        String productName = "ConfigProduct-" + UUID.randomUUID().toString();
        String configName = "us-standard-wifi-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName);
        assertEquals(201, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String productName = "QuantumLeap-" + UUID.randomUUID().toString();
        String configName = "standard-gpu-" + UUID.randomUUID().toString();
        String featureName = "distributed-training-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().formParam("description", "Feature for distributed training").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertEquals(201, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures200ContainsFeature() {
        String productName = "UltraLaptop-" + UUID.randomUUID().toString();
        String configName = "us-standard-16gb-" + UUID.randomUUID().toString();
        String featureName = "fingerprint-scanner-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().formParam("description", "Fingerprint scanner feature").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName);
        assertTrue(act.asString().contains(featureName));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsProductConfigurationWithValidTrue() {
        String productName = "MobileBackend-" + UUID.randomUUID().toString();
        String configName = "production-us-east-1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        assertTrue(act.asString().contains("\"valid\""));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationReturns204() {
        String productName = "IoT-Sensor-" + UUID.randomUUID().toString();
        String configName = "Premium-Tier-" + UUID.randomUUID().toString();
        String featureName = "Realtime-Analytics-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().formParam("description", "Realtime analytics").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertEquals(204, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductFeatureReturns204() {
        String productName = "SmartWatch-Pro-" + UUID.randomUUID().toString();
        String featureName = "heart-rate-monitor-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "Heart rate monitoring").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescriptionReturns200() {
        String productName = "AeroBook-Update-" + UUID.randomUUID().toString();
        String featureName = "backlit-keyboard-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "Initial description").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.").when().put("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductReturns204() {
        String productName = "DeleteProduct-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}", productName);
        assertEquals(204, act.statusCode());
    }
}