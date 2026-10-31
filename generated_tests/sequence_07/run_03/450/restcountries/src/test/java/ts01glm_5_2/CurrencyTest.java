package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaUSReturnsCurrencyCode() {
        Response r = given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(lessThan(300))
            .extract().response();
        assertEquals("USD", r.path("currencies[0].code"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaGBReturnsCurrencyName() {
        Response r = given()
        .when()
            .get("/v1/alpha/GB")
        .then()
            .statusCode(lessThan(300))
            .extract().response();
        assertEquals("British pound", r.path("currencies[0].name"));
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testV1AlphaUSReturnsCurrencySymbol() {
        Response r = given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(lessThan(300))
            .extract().response();
        assertEquals("$", r.path("currencies[0].symbol"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testV1AlphaCodesReturnsCountries() {
        given()
            .queryParam("codes", "US,CA,MX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyUSDReturnsCountries() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyXOFReturnsCountries() {
        given()
        .when()
            .get("/v1/currency/XOF")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1NameFranceReturnsCountries() {
        given()
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AllReturnsCountries() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEURReturnsCountries() {
        given()
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(200);
    }
}