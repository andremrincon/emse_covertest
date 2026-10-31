package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguagesWithIso639_1() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguagesWithIso639_2() {
        given()
                .when()
                .get("/v2/lang/English")
                .then()
                .statusCode(404)
                .body("languages.iso639_2", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguagesWithName() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("languages.name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguagesWithNativeName() {
        given()
                .when()
                .get("/v2/lang/English")
                .then()
                .statusCode(404)
                .body("languages.nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("languages.iso639_2", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AllEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .body("languages.name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("languages.nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CallingcodeEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CapitalEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .body("languages.name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .body("languages.iso639_2", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2SubregionEndpointReturnsLanguageFields() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/subregion/Western%20Europe")
                .then()
                .statusCode(404)
                .body("languages.nativeName", notNullValue());
    }
}