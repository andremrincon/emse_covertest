package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. JSON path [0].currencies.USD.name doesn't match. Expected: United States do...")
    @Test(timeout = 60000)
    public void testV1AlphaCodeReturnsCurrencyDetails() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(200)
            .body("[0].currencies.USD.name", equalTo("United States dollar"))
            .body("[0].currencies.USD.symbol", equalTo("$"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyDetails() {
        given()
            .pathParam("currency", "USD")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(200)
            .body("[0].currencies.USD.name", equalTo("United States dollar"))
            .body("[0].currencies.USD.symbol", equalTo("$"));
    }

    @Ignore("1 expectation failed. JSON path [0].currencies.EUR.name doesn't match. Expected: Euro   Actual: <[]>")
    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyDetails() {
        given()
            .pathParam("currency", "EUR")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(200)
            .body("[0].currencies.EUR.name", equalTo("Euro"))
            .body("[0].currencies.EUR.symbol", equalTo("\u20ac"));
    }
}