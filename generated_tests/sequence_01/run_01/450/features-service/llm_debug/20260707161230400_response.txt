package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        RestAssured.baseURI = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Success() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String description = "Measures the oxygen saturation (SpO2) of your blood on demand.";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response act = given().formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);

        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Duplicate_ProducesServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String description = "Initial description";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        Response act = given().formParam("description", "duplicate attempt")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName);

        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NoConfigurations_RemovesFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);

        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_WithActiveConfigurations_RemovesFromConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);

        assertEquals(204, act.getStatusCode());
    }
}