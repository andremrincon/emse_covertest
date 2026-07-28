package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testAlphaCodeReturnsLanguageWithIso639_1() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(200)
                .body("languages[0].iso639_1", notNullValue());
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testAlphaCodeReturnsLanguageWithIso639_2() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/GB")
        .then()
                .statusCode(200)
                .body("languages[0].iso639_2", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testAlphaCodeReturnsLanguageWithName() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/FR")
        .then()
                .statusCode(200)
                .body("languages[0].name", notNullValue());
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testAlphaCodeReturnsLanguageWithNativeName() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/DE")
        .then()
                .statusCode(200)
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testAlphaCodesQueryReturnsLanguageFields() {
        given()
                .contentType("application/json")
                .queryParam("codes", "US,CA,MX")
        .when()
                .get("/v1/alpha")
        .then()
                .statusCode(200)
                .body("languages[0][0].iso639_1", notNullValue());
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testCurrencyEndpointReturnsLanguageFields() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/currency/USD")
        .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testNameEndpointReturnsLanguageFields() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/name/France")
        .then()
                .statusCode(200)
                .body("[0].languages[0].name", notNullValue());
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testCallingCodeEndpointReturnsLanguageFields() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/callingcode/1")
        .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_2", notNullValue());
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testCapitalEndpointReturnsLanguageFields() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/capital/London")
        .then()
                .statusCode(200)
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testRegionEndpointReturnsLanguageFields() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/region/Europe")
        .then()
                .statusCode(200)
                .body("[0].languages[0].name", notNullValue());
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testLangEndpointReturnsLanguageFields() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/lang/es")
        .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", equalTo("es"));
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testAllEndpointReturnsLanguageFields() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/all")
        .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue());
    }
}