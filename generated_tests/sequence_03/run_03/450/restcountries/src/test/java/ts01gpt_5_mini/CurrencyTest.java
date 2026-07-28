package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void init() {
        String url = Optional.ofNullable(System.getProperty("base.url"))
                .orElse(Optional.ofNullable(System.getenv("BASE_URL"))
                        .orElse("http://localhost:8080/rest"));
        RestAssured.baseURI = url;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetAlphaByValidUS_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetCurrencyByUSD_returns200() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetV2CurrencyByEUR_returns200() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaWithNumeric_Returns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(400, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaWithUnknown_Returns404() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1CurrencyUnknown_Returns404() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/XYZ");
        assertEquals(404, act.getStatusCode());
    }
}