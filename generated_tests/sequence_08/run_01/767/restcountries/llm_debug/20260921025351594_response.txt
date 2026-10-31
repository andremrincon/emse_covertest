package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/all");

        response.then().header("Access-Control-Allow-Origin", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/alpha/US");

        response.then().header("Access-Control-Allow-Methods", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/currency/USD");

        response.then().header("Access-Control-Allow-Headers", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/name/France");

        response.then().header("Cache-Control", nullValue(String.class));
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV2Endpoint() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given().when().get("/v2/all");

        response.then().header("Access-Control-Allow-Origin", nullValue(String.class));
    }
}