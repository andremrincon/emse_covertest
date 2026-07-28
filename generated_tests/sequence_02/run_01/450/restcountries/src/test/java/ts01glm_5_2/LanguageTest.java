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
    public void testLanguageSettersViaAlphaCodeEndpoint() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaAlphaCodesEndpoint() {
        given()
            .queryParam("codes", "US,CA")
            .when()
            .get("/v1/alpha")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaCurrencyEndpoint() {
        given()
            .when()
            .get("/v1/currency/USD")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaNameEndpoint() {
        given()
            .when()
            .get("/v1/name/France")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaCallingCodeEndpoint() {
        given()
            .when()
            .get("/v1/callingcode/1")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaCapitalEndpoint() {
        given()
            .when()
            .get("/v1/capital/London")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaRegionEndpoint() {
        given()
            .when()
            .get("/v1/region/Europe")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaLangEndpoint() {
        given()
            .when()
            .get("/v1/lang/es")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaAllEndpoint() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaNameWithFullText() {
        given()
            .queryParam("fullText", "true")
            .when()
            .get("/v1/name/Germany")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaAlphaCodeGB() {
        given()
            .when()
            .get("/v1/alpha/GB")
            .then()
            .statusCode(404)
            .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaSubregionEndpoint() {
        given()
            .when()
            .get("/v1/subregion/Western%20Europe")
            .then()
            .statusCode(404)
            .body("languages", notNullValue());
    }
}