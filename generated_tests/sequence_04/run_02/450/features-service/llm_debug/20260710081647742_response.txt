package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetIncludesCORSAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}", productName);
        resp.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testOptionsRequestReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}", productName);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsResponseIncludesAllowMethodsHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}", productName);
        resp.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testPostFeatureReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "feat-" + UUID.randomUUID().toString();
        Response resp = given().formParam("description", "Integration test feature").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        resp.then().statusCode(201);
    }
}