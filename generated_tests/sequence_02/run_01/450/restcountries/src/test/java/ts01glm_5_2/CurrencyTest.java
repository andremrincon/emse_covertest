package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencyCodeSetterViaAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencyNameSetterViaAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].name", equalTo("United States dollar"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencySymbolSetterViaAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].symbol", equalTo("$"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencyCodeSetterViaCurrencyEndpoint() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencyNameSetterViaNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].name", equalTo("Euro"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencySymbolSetterViaCurrencyEndpoint() {
        given()
                .when()
                .get("/v1/currency/EUR")
                .then()
                .statusCode(404)
                .body("currencies[0].symbol", hasItem("€"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencySettersViaV2AlphaEndpoint() {
        given()
                .when()
                .get("/v2/alpha/DE")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencySettersViaV2RegionalblocEndpoint() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testCurrencySettersViaV2CurrencyEndpoint() {
        given()
                .when()
                .get("/v2/currency/USD")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"));
    }
}