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
    public static void setupBaseUri() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testGetProductInvokesFilterChainAndReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", productName);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testOptionsOnProductReturnsCORSAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().options("/products/{productName}", productName);
        assertEquals("*", act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "Desc").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testOptionsOnFeatureEndpointReturnsAllowMethodsHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().options("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals("POST, PUT, GET, OPTIONS, DELETE", act.getHeader("Access-Control-Allow-Methods"));
    }
}