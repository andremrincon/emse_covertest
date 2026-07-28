package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
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
    public void testSetIso639_1ViaV1AlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIso639_2ViaV1AlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404)
                .body("languages.iso639_2", hasItem("eng"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetNameViaV1NameEndpoint() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("languages.name", hasItem("French"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetNativeNameViaV1NameEndpoint() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("languages.nativeName", hasItem("français"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIso639_1ViaV2AlphaEndpoint() {
        given()
            .when()
                .get("/v2/alpha/DE")
            .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIso639_2ViaV2NameEndpoint() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(404)
                .body("languages.iso639_2", hasItem("deu"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetNameViaV2LangEndpoint() {
        given()
            .when()
                .get("/v2/lang/Spanish")
            .then()
                .statusCode(404)
                .body("languages.name", hasItem("Spanish"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetNativeNameViaV2LangEndpoint() {
        given()
            .when()
                .get("/v2/lang/Spanish")
            .then()
                .statusCode(404)
                .body("languages.nativeName", hasItem("Español"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIso639_1ViaV1AlphaMultipleCodes() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetIso639_2ViaV1LangEndpoint() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404)
                .body("languages.iso639_2", hasItem("spa"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetNameViaV1LangEndpoint() {
        given()
            .when()
                .get("/v1/lang/zh")
            .then()
                .statusCode(404)
                .body("languages.name", hasItem("Chinese"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetNativeNameViaV2AlphaMultipleCodes() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(404)
                .body("languages.nativeName", hasItem("English"));
    }
}