package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e = System.getenv("API_BASE");
            if (e == null || e.isEmpty()) {
                RestAssured.baseURI = "http://localhost:8080";
            } else {
                RestAssured.baseURI = e;
            }
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testOptionsRequestReturnsCorsAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response res = given().header("Origin", "http://example.com").when().options("/products/{productName}/features", productName).andReturn();
        assertEquals("*", res.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testGetFeaturesAfterProductCreationReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response res = given().when().get("/products/{productName}/features", productName).andReturn();
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureResponseContainsAllowMethodsHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response res = given().formParam("description", "Automated test feature").when().post("/products/{productName}/features/{featureName}", productName, featureName).andReturn();
        assertEquals("POST, PUT, GET, OPTIONS, DELETE", res.getHeader("Access-Control-Allow-Methods"));
    }
}