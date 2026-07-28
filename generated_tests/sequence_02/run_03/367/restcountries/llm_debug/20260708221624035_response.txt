package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_US_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_returns200() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().statusCode(200);
    }
}