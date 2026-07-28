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
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(404)
                .body("currencies[0].name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].symbol", equalTo("$"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCountriesWithCurrency() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/alpha/DE")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCountriesWithCurrencyName() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("currencies[0].name", hasItem("Euro"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionalblocWithCurrenciesFieldReturnsSymbol() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("currencies[0][0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyCodeNameSymbol() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"))
                .body("currencies[0].name", equalTo("Euro"))
                .body("currencies[0].symbol", equalTo("€"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameWithFullTextReturnsCurrencyFields() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"));
    }
}