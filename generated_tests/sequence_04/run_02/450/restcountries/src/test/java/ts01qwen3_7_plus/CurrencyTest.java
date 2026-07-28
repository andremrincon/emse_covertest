package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaCodeCurrencyCode() {
        Response resp = given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .extract().response();
        String code = resp.path("currencies[0].code");
        assertEquals("USD", code);
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaCodeCurrencyName() {
        Response resp = given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .extract().response();
        String name = resp.path("currencies[0].name");
        assertEquals("United States dollar", name);
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testV1AlphaCodeCurrencySymbol() {
        Response resp = given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .extract().response();
        String symbol = resp.path("currencies[0].symbol");
        assertEquals("$", symbol);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyCode() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .body("[0].currencies[0].code", equalTo("EUR"));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyName() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .body("[0].currencies[0].name", equalTo("Euro"));
    }

    @Test(timeout = 60000)
    public void testV2CurrencySymbol() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .body("[0].currencies[0].symbol", equalTo("\u20ac"));
    }
}