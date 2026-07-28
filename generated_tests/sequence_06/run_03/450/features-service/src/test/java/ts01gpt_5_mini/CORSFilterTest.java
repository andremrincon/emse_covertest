package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost");
        }
        String portStr = System.getenv("API_PORT");
        if (portStr == null || portStr.isEmpty()) {
            portStr = System.getProperty("api.port", "8080");
        }
        RestAssured.baseURI = base;
        RestAssured.port = Integer.parseInt(portStr);
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = RestAssured.given().contentType(ContentType.URLENC).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testOptionsRequest_containsCORSHeaders() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().options("/products/{productName}/features", productName);
        assertEquals("*", act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_returns204() {
        String productName = "test-prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().contentType(ContentType.URLENC).formParam("description", "to delete").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }
}