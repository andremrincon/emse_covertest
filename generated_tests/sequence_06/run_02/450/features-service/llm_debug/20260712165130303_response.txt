package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Product-" + java.util.UUID.randomUUID();
        String featureName = "Feature-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfiguration() {
        String productName = "Product-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "Product-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName + "/features").then().statusCode(200);
    }
}