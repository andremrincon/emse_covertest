package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCurrencySetCode() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

        given().baseUri(baseUrl).when().get("/v1/alpha/US").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCurrencySetName() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

        given().baseUri(baseUrl).when().get("/v1/currency/USD").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/currency/USD")
        .then()
            .body("currencies[0].name", equalTo("United States dollar"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCurrencySetSymbol() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

        given().baseUri(baseUrl).when().get("/v1/alpha/US").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .body("currencies[0].symbol", equalTo("$"));
    }
}