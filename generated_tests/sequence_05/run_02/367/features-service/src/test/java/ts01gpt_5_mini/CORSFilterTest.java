package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base", System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testOptionsReturnsCorsAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}/features", productName);
        resp.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testGetFeaturesReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        resp.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testOptionsAccessControlMaxAgeHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}/features", productName);
        resp.then().header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void testOptionsAllowMethodsContainsPost() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}/features", productName);
        resp.then().header("Access-Control-Allow-Methods", containsString("POST"));
    }
}