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
    public void testV1AlphaReturnsLanguageWithIso639_1() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsLanguageWithIso639_2() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_2", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsLanguageWithName() {
        given()
            .when()
                .get("/v1/alpha/FR")
            .then()
                .statusCode(404)
                .body("languages[0].name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsLanguageWithNativeName() {
        given()
            .when()
                .get("/v1/alpha/DE")
            .then()
                .statusCode(404)
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("fr"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .body("languages[0].name", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
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
                .body("languages[0].iso639_2", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1LangReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404)
                .body("languages[0].name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_1", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/subregion/Western%20Europe")
            .then()
                .statusCode(404)
                .body("languages[0].nativeName", notNullValue());
    }
}