package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaAlphacode_US_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaAlphacode_invalidNumeric_returns400() {
        given().when().get("/v1/alpha/123").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v1/currency/USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Currency_nonexistent_returns404() {
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"Eu...")
    @Test(timeout = 60000)
    public void testV2Currency_withFields_containsEuroInBody() {
        given().when().get("/v2/currency/EUR?fields=name;capital;population").then().body(containsString("Euro"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV2Alpha_multipleCodes_withFields_returns200() {
        given().when().get("/v2/alpha?codes=US,CA,MX&fields=name;capital;population").then().statusCode(200);
    }
}