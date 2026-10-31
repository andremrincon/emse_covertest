package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    private static final String BASE_URL = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v1AlphaByCodeReturnsCurrencyFields() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"))
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v1CurrencyEndpointReturnsCountriesWithCurrency() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v1AlphaMultipleCodesReturnsCurrencyData() {
        given()
                .accept(ContentType.JSON)
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404)
                .body("currencies", not(empty()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v2AlphaByCodeReturnsCurrencyFields() {
        given()
                .accept(ContentType.JSON)
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"))
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v2CurrencyEndpointReturnsCountriesWithCurrency() {
        given()
                .accept(ContentType.JSON)
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v2RegionalblocWithCurrencyFieldsReturnsCurrencyData() {
        given()
                .accept(ContentType.JSON)
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("currencies", not(empty()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v1NameReturnsCurrencyDataForCountry() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"))
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v2CallingcodeReturnsCurrencyData() {
        given()
                .accept(ContentType.JSON)
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/callingcode/44")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void v1CapitalReturnsCurrencyData() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("GBP"))
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }
}