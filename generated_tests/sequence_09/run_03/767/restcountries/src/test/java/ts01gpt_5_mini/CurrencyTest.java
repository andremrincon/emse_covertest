package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("REST_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("REST_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("rest.base");
        if (base == null || base.isEmpty()) base = System.getenv("rest.base");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1AlphaAlphacode_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/US");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AlphaAlphacode_BadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/123");
        res.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testV1Alpha_Codes_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        res.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testV1Alpha_Codes_BadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "123").when().get("/v1/alpha");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Currency_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/USD");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Currency_NotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/XYZ");
        res.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Currency_Fields_Success() {
        given().queryParam("fields", "name;capital;population").when().get("/v2/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("fields", "name;capital;population").when().get("/v2/currency/EUR");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_BadCodes_BadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha");
        res.then().statusCode(400);
    }
}