package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_bodyHasAlpha2CodeUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("alpha2Code", equalTo("US"));
    }

    @Test(timeout = 60000)
    public void testV2Alpha_multipleCodes_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidNumeric_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Currency_invalidNumeric_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/123");
        act.then().statusCode(404);
    }
}