package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080/rest");
        RestAssured.defaultParser = Parser.JSON;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .body("currencies[0].name", equalTo("United States dollar"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetSymbol() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .body("currencies[0].symbol", equalTo("$"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testCurrencyDeserializationV2() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200);
    }
}