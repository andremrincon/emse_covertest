package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV2AlphaByCodeReturnsLanguageData() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AlphaMultipleCodesReturnLanguageData() {
        given()
                .queryParam("codes", "US;CA;MX")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsLanguageData() {
        given()
                .when()
                .get("/v2/name/France")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2NameFullTextReturnsLanguageData() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/lang/es")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AllReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2SubregionReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/subregion/Western%20Europe")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CapitalReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/capital/London")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CallingCodeReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CurrencyReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2DemonymReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(200)
                .body("languages", notNullValue());
    }
}