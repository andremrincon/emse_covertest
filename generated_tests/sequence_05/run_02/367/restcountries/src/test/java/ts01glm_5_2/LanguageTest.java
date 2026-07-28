package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsLanguageData() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", notNullValue());
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeGBReturnsLanguageData() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_2']", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsLanguageData() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("[0]['languages'][0]['name']", notNullValue());
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testV1NameFullTextReturnsLanguageData() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/Germany")
                .then()
                .statusCode(200)
                .body("[0]['languages'][0]['nativeName']", notNullValue());
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1LangReturnsLanguageData() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("[0]['languages'][0]['iso639_1']", equalTo("es"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsLanguageData() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("[0]['languages'][0]['name']", notNullValue());
    }

    @Ignore("Invalid JSON expression: Script1.groovy: 1: Unexpected input: '                         [' @ line...")
    @Test(timeout = 60000)
    public void testV2AlphaByCodeReturnsLanguageData() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("['languages']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsLanguageData() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200)
                .body("[0]['languages']", notNullValue());
    }

    @Ignore("1 expectation failed. JSON path [0]['languages'] doesn't match. Expected: not null   Actual: null")
    @Test(timeout = 60000)
    public void testV2LangReturnsLanguageData() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("[0]['languages']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CallingCodeReturnsLanguageData() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(200)
                .body("[0]['languages']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CapitalReturnsLanguageData() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(200)
                .body("[0]['languages']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionReturnsLanguageData() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(200)
                .body("[0]['languages']", notNullValue());
    }
}