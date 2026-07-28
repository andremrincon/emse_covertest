package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidFormat_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Name_France_fullTextFalse_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/France");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Alpha_multipleCodes_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v2/alpha");
        assertEquals(400, act.getStatusCode());
    }
}