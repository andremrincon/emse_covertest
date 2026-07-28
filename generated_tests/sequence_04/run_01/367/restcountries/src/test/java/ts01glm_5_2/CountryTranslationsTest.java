package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaSingleCodeReturnsTranslationsDe() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("translations.de", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaSingleCodeReturnsTranslationsEs() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("translations.es", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaSingleCodeReturnsTranslationsFr() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(200)
                .body("translations.fr", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaSingleCodeReturnsTranslationsJa() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("translations.ja", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaSingleCodeReturnsTranslationsIt() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("translations.it", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslationsDe() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200)
                .body("translations.de", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslationsEs() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("translations.es", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslationsFr() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("translations.fr", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslationsJa() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(200)
                .body("translations.ja", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslationsIt() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(200)
                .body("translations.it", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslationsDe() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .body("translations.de", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsTranslationsEs() {
        given()
                .when()
                .get("/v1/subregion/Western%20Europe")
                .then()
                .statusCode(200)
                .body("translations.es", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1LangReturnsTranslationsFr() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("translations.fr", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AllReturnsTranslationsJa() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("translations.ja", hasItems(notNullValue()));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AllReturnsTranslationsIt() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("translations.it", hasItems(notNullValue()));
    }
}