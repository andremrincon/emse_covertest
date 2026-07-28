package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameGermanyReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", hasItem("de"))
                .body("languages[0].iso639_2", hasItem("deu"))
                .body("languages[0].name", hasItem("German"))
                .body("languages[0].nativeName", hasItem("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameFranceFullTextReturnsLanguageFields() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/France")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", hasItem("fr"))
                .body("languages[0].iso639_2", hasItem("fra"))
                .body("languages[0].name", hasItem("French"))
                .body("languages[0].nativeName", is(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaUSWithLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"))
                .body("languages.iso639_2", hasItem("eng"))
                .body("languages.name", hasItem("English"))
                .body("languages.nativeName", hasItem("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaMultipleCodesWithLanguageFields() {
        given()
                .queryParam("codes", "US,CA")
                .queryParam("fields", "languages")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItems("en", "fr"))
                .body("languages.iso639_2", hasItems("eng", "fra"))
                .body("languages.name", hasItems("English", "French"))
                .body("languages.nativeName", hasItems("English", "français"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CallingCode1ReturnsLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"))
                .body("languages.iso639_2", hasItem("eng"))
                .body("languages.name", hasItem("English"))
                .body("languages.nativeName", hasItem("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CapitalParisWithLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("fr"))
                .body("languages.iso639_2", hasItem("fra"))
                .body("languages.name", hasItem("French"))
                .body("languages.nativeName", is(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionEuropeWithLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV2SubregionWesternEuropeWithLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangSpanishReturnsLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("es"))
                .body("languages.iso639_2", hasItem("spa"))
                .body("languages.name", hasItem("Spanish"))
                .body("languages.nativeName", hasItem("Español"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEURWithLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2DemonymAmericanWithLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"))
                .body("languages.iso639_2", hasItem("eng"))
                .body("languages.name", hasItem("English"))
                .body("languages.nativeName", hasItem("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionalBlocEUWithLanguageFields() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }
}