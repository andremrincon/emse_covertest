package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        assertEquals(204, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName);

        assertEquals(204, response.getStatusCode());
    }
}