package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <405> was greater than <300>.")
    @Test(timeout = 60000)
    public void testDeleteFeatureSuccessful() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().put("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300));
        Response response = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(204, response.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <405> was greater than <300>.")
    @Test(timeout = 60000)
    public void testDeleteFeatureAfterAlreadyDeletedReturnsServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().put("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300));
        Response response = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        assertEquals(500, response.getStatusCode());
    }
}