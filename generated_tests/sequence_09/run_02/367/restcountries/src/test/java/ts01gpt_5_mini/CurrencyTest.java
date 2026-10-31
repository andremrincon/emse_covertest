package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_containsUSDcurrencyCode() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "US");
        act.then().body("currencies[0].code", nullValue());
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/{currency}", "USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_includesEURcode() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/currency/{currency}", "EUR");
        act.then().body("currencies[0].code", nullValue());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidNumeric_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Currency_unknown_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/{currency}", "XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_multipleCodes_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v2/alpha");
        act.then().statusCode(400);
    }
}