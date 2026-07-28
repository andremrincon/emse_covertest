package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureSuccessfully() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName);

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureInvalidState() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName);

        response.then().statusCode(204);
    }
}