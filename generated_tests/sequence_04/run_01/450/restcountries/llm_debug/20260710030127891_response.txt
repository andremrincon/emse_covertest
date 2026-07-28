package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
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
    public void testV1AlphaUS_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1CurrencyUSD_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEUR_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1NameFrance_bodyContainsEuro() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France");
        String body = resp.asString();
        assertTrue(body.contains("Euro") || body.contains("\u20ac"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaUS_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        assertEquals(200, resp.getStatusCode());
    }
}