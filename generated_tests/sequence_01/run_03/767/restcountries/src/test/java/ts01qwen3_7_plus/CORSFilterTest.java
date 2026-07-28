package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOrigin() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(200).header("Access-Control-Allow-Origin", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethods() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France");
        response.then().statusCode(200).header("Access-Control-Allow-Methods", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeaders() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/capital/London");
        response.then().statusCode(200).header("Access-Control-Allow-Headers", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCacheControl() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/region/Europe");
        response.then().statusCode(200).header("Cache-Control", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testFilterChainExecution() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD");
        response.then().statusCode(200);
    }
}