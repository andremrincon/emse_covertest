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
    public static void init() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetCodeViaV1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetNameViaV1Currency_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().body(containsString("USD"));
    }

    @Test(timeout = 60000)
    public void testSetSymbolViaV2Currency_EUR() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().statusCode(200);
    }
}