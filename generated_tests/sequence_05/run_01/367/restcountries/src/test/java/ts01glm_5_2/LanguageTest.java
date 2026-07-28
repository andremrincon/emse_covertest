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
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1AlphaUsReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", notNullValue());
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1AlphaUsLanguageIso639_2() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_2']", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaUsLanguageName() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['name']", notNullValue());
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testV1AlphaUsLanguageNativeName() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0]['nativeName']", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameFranceReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0]['iso639_1']", equalTo("fr"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameFranceLanguageNativeName() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0]['nativeName']", notNullValue());
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1LangEsReturnsCountriesWithLanguages() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("languages[0][0]['iso639_1']", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1LangEsLanguageNameField() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("languages[0][0]['name']", notNullValue());
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1CurrencyUsdReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("languages[0][0]['iso639_1']", notNullValue());
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1RegionEuropeReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .body("languages[0][0]['iso639_2']", notNullValue());
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testV1CapitalLondonReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(200)
                .body("languages[0][0]['nativeName']", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1Callingcode1ReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(200)
                .body("languages[0][0]['name']", notNullValue());
    }
}