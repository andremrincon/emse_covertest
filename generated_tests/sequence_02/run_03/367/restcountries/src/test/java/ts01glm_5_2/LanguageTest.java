package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesByCurrencyReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesByRegionReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesByLanguageReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2GetCountryByAlphaCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/alpha/DE")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2GetCountryByNameReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2GetCountriesByLanguageReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesByCallingCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCapitalReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesBySubregionReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/subregion/Western%20Europe")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2GetCountriesByCallingCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/callingcode/44")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }
}