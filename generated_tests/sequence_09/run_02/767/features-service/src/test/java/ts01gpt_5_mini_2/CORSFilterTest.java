package ts01gpt_5_mini_2;

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
        String env = System.getProperty("api.base", System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGetFeaturesReturns200() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName).andReturn();
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testOptionsPreflightHandledWithoutChain() {
        String productName = "test-product-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given()
                .header("Origin", "http://example.com")
                .header("Access-Control-Request-Method", "POST")
                .when()
                .options("/products/{productName}/features", productName)
                .andReturn();
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetFeaturesReturnsCORSAllowOriginHeader() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName).andReturn();
        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin"));
    }
}