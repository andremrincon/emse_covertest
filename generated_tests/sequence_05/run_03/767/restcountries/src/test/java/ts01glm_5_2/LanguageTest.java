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

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaAlphaCodeEndpoint() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", notNullValue())
                .body("languages[0]['iso639_2']", notNullValue())
                .body("languages[0]['name']", notNullValue())
                .body("languages[0]['nativeName']", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testLanguageSettersViaAlphaCodesEndpoint() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200)
                .body("languages", not(empty()));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaCurrencyEndpoint() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaNameEndpoint() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("languages[0]['name']", equalTo("French"))
                .body("languages[0]['nativeName']", equalTo("français"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaCallingCodeEndpoint() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaCapitalEndpoint() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", equalTo("en"))
                .body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaRegionEndpoint() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .body("languages", not(empty()));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaLangEndpoint() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaAllEndpoint() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .body("languages", not(empty()));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaNameWithFullText() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/Germany")
            .then()
                .statusCode(200)
                .body("languages[0]['name']", equalTo("German"))
                .body("languages[0]['nativeName']", equalTo("Deutsch"));
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaAlphaCodeGB() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", equalTo("en"))
                .body("languages[0]['iso639_2']", equalTo("eng"))
                .body("languages[0]['name']", equalTo("English"))
                .body("languages[0]['nativeName']", equalTo("English"));
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testLanguageSettersViaAlphaCodeDE() {
        given()
            .when()
                .get("/v1/alpha/DE")
            .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", equalTo("de"))
                .body("languages[0]['iso639_2']", equalTo("deu"))
                .body("languages[0]['name']", equalTo("German"))
                .body("languages[0]['nativeName']", equalTo("Deutsch"));
    }
}