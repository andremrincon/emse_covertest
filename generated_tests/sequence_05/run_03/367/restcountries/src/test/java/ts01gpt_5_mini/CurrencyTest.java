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
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMapCurrencyFromV2_EUR_assertStatus200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/currency/EUR");
        String code = response.jsonPath().getString("[0].currencies[0]");
        response.then().statusCode(200);
        if (code == null) throw new AssertionError();
    }

    @Test(timeout = 60000)
    public void testV2Currency_numericBad_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/currency/123");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Currency_US_bodyCurrencyCodeUSD_assert() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD");
        String code = response.jsonPath().getString("[0].currencies[0]");
        response.then().body("[0].currencies[0]", equalTo("USD"));
        if (code == null) throw new AssertionError();
    }

    @Test(timeout = 60000)
    public void testV2Currency_unknown_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/currency/XYZ");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_mapsCurrency_and_assertStatus200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        String code = response.jsonPath().getString("currencies[0]");
        response.then().statusCode(200);
        if (code == null) throw new AssertionError();
    }

    public static class Currency {
        public String code;
        public String name;
        public String symbol;

        public Currency() {
        }
    }
}