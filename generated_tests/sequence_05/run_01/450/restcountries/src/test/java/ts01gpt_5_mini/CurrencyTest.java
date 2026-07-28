package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore


    @Test(timeout = 60000)
    public void testSetCodeViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        Assert.assertTrue(act.getBody().asString().contains("\"code\":\"USD\""));
    }

    @Test(timeout = 60000)
    public void testCurrencyEndpointReturns200ForUSD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSetSymbolViaV2CurrencyEUR() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().body("[0].currencies[0].symbol", equalTo("€"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testSetNameViaV1NameFrance() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        Assert.assertTrue(act.getBody().asString().contains("\"name\":\"Euro\""));
    }

    @Test(timeout = 60000)
    public void testV1CurrencyInvalidReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/XYZ");
        Assert.assertEquals(404, act.getStatusCode());
    }
}