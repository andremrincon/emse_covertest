package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class CurrencyTest {

    @BeforeClass
    public static void setUpClass() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1CurrencyValidUSD_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/currency/{currency}", "USD");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEUR_bodyContainsEUR() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/currency/{currency}", "EUR");
        r.then().body("[0].currencies[0].code", equalTo("EUR"));
    }

    @Test(timeout = 60000)
    public void testV1AlphaUS_bodyHasAlpha2Code() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/{alphacode}", "US");
        r.then().body("alpha2Code", equalTo("US"));
    }

    @Test(timeout = 60000)
    public void testV1AlphaBadFormat_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/{alphacode}", "123");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyNotFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/currency/{currency}", "XYZ");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameFullTextTrue_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fullText", true).when().get("/v2/name/{name}", "Germany");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AlphaMultipleCodes_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("codes", "US,CA").when().get("/v2/alpha");
        r.then().statusCode(400);
    }
}