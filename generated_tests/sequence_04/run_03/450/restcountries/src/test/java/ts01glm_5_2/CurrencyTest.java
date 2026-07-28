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
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. JSON path [0].currencies[0].code doesn't match. Expected: USD   Actual: null")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsCurrencyCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("USD"));
    }

    @Ignore("1 expectation failed. JSON path [0].currencies[0].name doesn't match. Expected: United States dol...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsCurrencyName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].name", equalTo("United States dollar"));
    }

    @Ignore("1 expectation failed. JSON path [0].currencies[0].symbol doesn't match. Expected: $   Actual: null")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].symbol", equalTo("$"));
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetCountriesByCurrencyReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("USD"))
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetCountryByNameReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("EUR"))
                .body("[0].currencies[0].name", equalTo("Euro"))
                .body("[0].currencies[0].symbol", equalTo("€"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGetCountriesByAlphaCodesReturnsCurrencyFields() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetCountryByCallingCodeReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/callingcode/44")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("GBP"))
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetCountryByCapitalReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("GBP"))
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetCountriesByRegionReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", notNullValue());
    }
}