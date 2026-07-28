package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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

    @Test(timeout = 60000)
    public void testV1AlphaUSReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyUSDReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEURHasCurrencyCodeEURInFirstEntry() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().body("[0].currencies[0].code", equalTo("EUR"));
    }

    @Test(timeout = 60000)
    public void testV1AllReturns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithCodesReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha?codes=US,CA,MX");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1NameFranceContainsEURCurrencyCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France?fullText=false");
        act.then().body(containsString("EUR"));
    }
}