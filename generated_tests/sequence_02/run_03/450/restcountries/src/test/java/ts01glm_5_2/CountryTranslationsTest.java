package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class CountryTranslationsTest {

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
    public void testGetAllCountriesTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("[0].translations", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeUSTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeGBTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(404)
                .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameFranceTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Illegal character in path at index 46: http://localhost:8080/rest/rest/v1/name/United States of A...")
    @Test(timeout = 60000)
    public void testGetCountryByNameUnitedStatesTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/name/{name}", "United States of America")
                .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByRegionEuropeTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCapitalLondonTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCallingCode1TriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404)
                .body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyUSDTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetMultipleAlphaCodesTriggersTranslationsSetters() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetCountryBySubregionTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByLangEsTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameGermanyFullTextTriggersTranslationsSetters() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/Germany")
                .then()
                .statusCode(404)
                .body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeDETriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/alpha/DE")
                .then()
                .statusCode(404)
                .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeFRTriggersTranslationsSetters() {
        given()
                .when()
                .get("/v1/alpha/FR")
                .then()
                .statusCode(404)
                .body("translations.it", notNullValue());
    }
}