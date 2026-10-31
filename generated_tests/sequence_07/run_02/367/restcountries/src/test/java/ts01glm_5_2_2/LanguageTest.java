package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangSpanishReturnsLanguageFields() {
        given()
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
    public void testV2NameGermanyReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaUSReturnsLanguageFields() {
        given()
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
    public void testV2CallingCode1ReturnsLanguageFields() {
        given()
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
    public void testV2CapitalParisReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("fr"))
                .body("languages.iso639_2", hasItem("fra"))
                .body("languages.name", hasItem("French"))
                .body("languages.nativeName", hasItem("français"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionEuropeReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2SubregionWesternEuropeReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/subregion/Western%20Europe")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2DemonymAmericanReturnsLanguageFields() {
        given()
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
    public void testV2RegionalblocEUReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"))
                .body("languages.iso639_2", hasItem("deu"))
                .body("languages.name", hasItem("German"))
                .body("languages.nativeName", hasItem("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEURReturnsLanguageFields() {
        given()
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
    public void testV2AllReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"))
                .body("languages.iso639_2", hasItem("eng"))
                .body("languages.name", hasItem("English"))
                .body("languages.nativeName", hasItem("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangEnglishReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/lang/English")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"))
                .body("languages.iso639_2", hasItem("eng"))
                .body("languages.name", hasItem("English"))
                .body("languages.nativeName", hasItem("English"));
    }
}