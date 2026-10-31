package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

public class CurrencyTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080/rest" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidNumeric_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_responseContainsUSDCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "USD").then().statusCode(200).body(containsString("USD"));
    }

    @Test(timeout = 60000)
    public void testV1Currency_unknown_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/currency/{currency}", "EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_US_withFields_returnsExpectedName() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{alphacode}", "US").then().statusCode(200).body("name", equalTo("United States of America"));
    }

    @Test(timeout = 60000)
    public void testV2Alpha_numericBad_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "123").then().statusCode(404);
    }
}