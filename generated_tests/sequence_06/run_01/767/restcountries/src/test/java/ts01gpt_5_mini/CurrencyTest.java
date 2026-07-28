package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1AlphaUSReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyUSDReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/{currency}", "USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEURBodyContainsEuro() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "EUR");
        act.then().body(containsString("Euro"));
    }
}