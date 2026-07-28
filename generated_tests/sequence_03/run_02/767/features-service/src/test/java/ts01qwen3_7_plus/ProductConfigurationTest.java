package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Test(timeout = 60000)
    public void testGetConfigurationCoversAvailableFeatures() {
        String productName = "AeroBook-Pro-15-" + System.nanoTime();
        String featureName = "backlit-keyboard-" + System.nanoTime();
        String configName = "us-standard-16gb-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        Response response = given().pathParam("productName", productName).pathParam("configurationName", configName).when().get("/products/{productName}/configurations/{configurationName}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCoversAvailableFeatures() {
        String productName = "Smartwatch-Series-8-" + System.nanoTime();
        String featureName = "Blood-Oxygen-Sensor-" + System.nanoTime();
        String configName = "standard-gpu-cluster-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        Response response = given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesCoversAvailableFeatures() {
        String productName = "QuantumLeap-AI-Platform-" + System.nanoTime();
        String featureName = "distributed-training-" + System.nanoTime();
        String configName = "standard-gpu-cluster-" + System.nanoTime();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        Response response = given().pathParam("productName", productName).pathParam("configurationName", configName).when().get("/products/{productName}/configurations/{configurationName}/features");

        response.then().statusCode(200);
    }
}