package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void v1Alpha_US_shouldReturn200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void v1Alpha_invalidFormatShouldReturn400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1Currency_USD_shouldReturn200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void v1Currency_notFoundShouldReturn404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void v2Currency_EUR_shouldReturn200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void v2Currency_malformedShouldReturn400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1Alpha_multipleCodesShouldReturn200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }
}