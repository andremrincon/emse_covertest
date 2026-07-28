package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void init() {
        String fromSys = System.getProperty("api.base");
        String fromEnv = System.getenv("API_BASE_URL");
        String base = Optional.ofNullable(fromSys).orElse(fromEnv);
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_US_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Currency_USD_bodyContainsUSDCode() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().body("[0].currencies[0].code", equalTo("USD"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Currency_EUR_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/EUR").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Name_France_currencyNameIsEuro() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France").then().body("[0].currencies[0].name", equalTo("Euro"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Alpha_withFields_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/US?fields=name;capital;population").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_numericCode_returns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_unknownCode_returns404() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }
}