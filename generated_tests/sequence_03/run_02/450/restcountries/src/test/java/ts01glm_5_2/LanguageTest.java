package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
    public void testLanguageSettersViaV1AlphaCode() {
        given()
                .accept(ContentType.JSON)
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
    public void testLanguageSettersViaV1Name() {
        given()
                .accept(ContentType.JSON)
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
    public void testLanguageSettersViaV1Lang() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(404)
                .body("languages[0].iso639_1", hasItem("es"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV1Currency() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("languages[0].name", hasItem("English"));
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV1CallingCode() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/callingcode/44")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV1Capital() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV1Region() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV2AlphaCode() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v2/alpha/DE")
                .then()
                .statusCode(404)
                .body("languages.deu", equalTo("German"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Name() {
        given()
                .accept(ContentType.JSON)
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
    public void testLanguageSettersViaV2Lang() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("languages[0].name", hasItem("Spanish"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV1AlphaMultipleCodes() {
        given()
                .accept(ContentType.JSON)
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404)
                .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV1All() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("languages", notNullValue());
    }
}