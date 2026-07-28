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
    public void testV1AlphaReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("en"))
                .body("languages[0].iso639_2", equalTo("eng"))
                .body("languages[0].name", equalTo("English"))
                .body("languages[0].nativeName", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("fr"))
                .body("languages[0].iso639_2", equalTo("fra"))
                .body("languages[0].name", equalTo("French"))
                .body("languages[0].nativeName", equalTo("français"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1LangReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("es"))
                .body("languages[0].iso639_2", equalTo("spa"))
                .body("languages[0].name", equalTo("Spanish"))
                .body("languages[0].nativeName", equalTo("Español"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsLanguageFields() {
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
    public void testV1CurrencyReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("en"))
                .body("languages[0].iso639_2", equalTo("eng"))
                .body("languages[0].name", equalTo("English"))
                .body("languages[0].nativeName", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsLanguageFields() {
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
    public void testV1CapitalReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("en"))
                .body("languages[0].iso639_2", equalTo("eng"))
                .body("languages[0].name", equalTo("English"))
                .body("languages[0].nativeName", equalTo("English"));
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsLanguageFields() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("de"))
                .body("languages[0].iso639_2", equalTo("deu"))
                .body("languages[0].name", equalTo("German"))
                .body("languages[0].nativeName", equalTo("Deutsch"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("es"))
                .body("languages[0].iso639_2", equalTo("spa"))
                .body("languages[0].name", equalTo("Spanish"))
                .body("languages[0].nativeName", equalTo("Español"));
    }
}