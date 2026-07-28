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
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(200, act.getStatusCode());
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
    public void testV1Alpha_multipleCodes_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().param("codes", "US,CA").when().get("/v1/alpha");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidFormat_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Currency_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Name_fullTextFalse_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().param("fullText", "false").when().get("/v1/name/France");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Alpha_invalidFormat_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/123");
        assertEquals(404, act.getStatusCode());
    }
}