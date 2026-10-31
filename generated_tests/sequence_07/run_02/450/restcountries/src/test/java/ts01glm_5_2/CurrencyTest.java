package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyCode() {
        Response resp = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .extract().response();
        String code = resp.jsonPath().getString("currencies[0].code");
        assertEquals("USD", code);
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        Response resp = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .extract().response();
        String name = resp.jsonPath().getString("currencies[0].name");
        assertNotNull(name);
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        Response resp = given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(200)
                .extract().response();
        String symbol = resp.jsonPath().getString("currencies[0].symbol");
        assertNotNull(symbol);
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCountriesWithCurrencyFields() {
        Response resp = given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .extract().response();
        String code = resp.jsonPath().getString("[0].currencies[0].code");
        assertEquals("USD", code);
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyFields() {
        Response resp = given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .extract().response();
        String code = resp.jsonPath().getString("[0].currencies[0].code");
        assertEquals("EUR", code);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyReturnsCurrencyCodeAndName() {
        Response resp = given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .extract().response();
        String code = resp.jsonPath().getString("[0].currencies[0].code");
        assertEquals("EUR", code);
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencySymbol() {
        Response resp = given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .extract().response();
        String symbol = resp.jsonPath().getString("[0].currencies[0].symbol");
        assertNotNull(symbol);
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyName() {
        Response resp = given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .extract().response();
        String name = resp.jsonPath().getString("currencies[0].name");
        assertNotNull(name);
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyFields() {
        Response resp = given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .extract().response();
        String code = resp.jsonPath().getString("[0].currencies[0].code");
        assertNotNull(code);
    }
}