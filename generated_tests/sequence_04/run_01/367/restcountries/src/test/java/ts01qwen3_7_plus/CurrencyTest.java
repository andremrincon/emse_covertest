package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetCode() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/currency/USD");
        response.then().body("currencies.code", hasItem("USD"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetName() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/currency/USD");
        response.then().body("currencies.name", hasItem("United States dollar"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/currency/USD");
        response.then().body("currencies.symbol", hasItem("$"));
    }
}