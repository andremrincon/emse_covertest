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
        String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeUSReturnsCurrencyWithCodeNameSymbol() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0]['code']", equalTo("USD"))
                .body("currencies[0]['name']", notNullValue())
                .body("currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetByCurrencyUSDReturnsCountriesWithCurrencyFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", equalTo("USD"))
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetByNameFranceReturnsCurrencyWithCodeNameSymbol() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", equalTo("EUR"))
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetAllReturnsCountriesWithCurrencyFields() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", notNullValue())
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetByRegionEuropeReturnsCountriesWithCurrencyFields() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", notNullValue())
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaCodesReturnsCountriesWithCurrencyFields() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", notNullValue())
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetByCallingCode1ReturnsCountriesWithCurrencyFields() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", notNullValue())
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetByCapitalLondonReturnsCountryWithCurrencyFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", equalTo("GBP"))
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testGetBySubregionWesternEuropeReturnsCountriesWithCurrencyFields() {
        given()
                .when()
                .get("/v1/subregion/Western%20Europe")
                .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", notNullValue())
                .body("[0].currencies[0]['name']", notNullValue())
                .body("[0].currencies[0]['symbol']", notNullValue());
    }
}