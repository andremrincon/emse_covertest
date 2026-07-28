package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String productName = "Smartwatch-" + UUID.randomUUID().toString();
        String featureName = "Blood-Oxygen-Sensor-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "Measures SpO2").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testOptionsRequest_setsCORSHeaders() {
        String productName = "AeroBook-Pro-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().options("/products/{productName}/features", productName);
        assertEquals("*", act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testGetFeatures_returns200() {
        String productName = "AeroBook-Pro-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", productName);
        assertEquals(200, act.getStatusCode());
    }
}