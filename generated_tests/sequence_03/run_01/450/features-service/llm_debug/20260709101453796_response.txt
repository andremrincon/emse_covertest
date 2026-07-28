package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaAddFeatureToConfiguration() {
        String productName = "QuantumLeap-AI-Platform-" + System.currentTimeMillis();
        String featureName = "distributed-training-" + System.currentTimeMillis();
        String configurationName = "standard-gpu-cluster-" + System.currentTimeMillis();

        given().baseUri(baseUrl).pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(201);
    }
}