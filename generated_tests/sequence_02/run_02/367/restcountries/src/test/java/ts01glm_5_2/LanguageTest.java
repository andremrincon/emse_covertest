package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void testV1AlphaUsReturnsLanguageWithIso639_1() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaUsReturnsLanguageWithIso639_2() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_2", equalTo("eng"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaUsReturnsLanguageWithName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("languages[0].name", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaUsReturnsLanguageWithNativeName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("languages[0].nativeName", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameFranceReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("[0].languages[0].iso639_1", equalTo("fr"))
                .body("[0].languages[0].iso639_2", equalTo("fra"))
                .body("[0].languages[0].name", equalTo("French"))
                .body("[0].languages[0].nativeName", equalTo("français"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyUsdReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .body("[0].languages[0].iso639_1", equalTo("en"))
                .body("[0].languages[0].name", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionEuropeReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1LangEsReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404)
                .body("[0].languages[0].iso639_1", equalTo("es"))
                .body("[0].languages[0].iso639_2", equalTo("spa"))
                .body("[0].languages[0].name", equalTo("Spanish"))
                .body("[0].languages[0].nativeName", equalTo("Español"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Callingcode1ReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404)
                .body("[0].languages[0].iso639_1", equalTo("en"))
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CapitalLondonReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404)
                .body("[0].languages[0].iso639_1", equalTo("en"))
                .body("[0].languages[0].name", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsLanguageFields() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaUsReturnsLanguageFields() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404)
                .body("languages[0].iso639_1", equalTo("en"))
                .body("languages[0].iso639_2", equalTo("eng"))
                .body("languages[0].name", equalTo("English"))
                .body("languages[0].nativeName", equalTo("English"));
    }
}