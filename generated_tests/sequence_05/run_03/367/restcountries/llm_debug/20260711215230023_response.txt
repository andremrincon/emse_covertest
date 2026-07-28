package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import org.junit.Before;
import org.junit.Test;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testAlphaCodeDeTranslation() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaCodeEsTranslation() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaCodeFrTranslation() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaCodeJaTranslation() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaCodeItTranslation() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void testNameEndpointDeTranslation() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .body("[0].translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testCurrencyEndpointEsTranslation() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .body("[0].translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testCallingCodeEndpointFrTranslation() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .body("[0].translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testCapitalEndpointJaTranslation() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .body("[0].translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRegionEndpointItTranslation() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .body("[0].translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSubregionEndpointDeTranslation() {
        given()
            .when()
                .get("/v1/subregion/Western%20Europe")
            .then()
                .body("[0].translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testLangEndpointEsTranslation() {
        given()
            .when()
                .get("/v1/lang/de")
            .then()
                .body("[0].translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAllEndpointFrTranslation() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .body("[0].translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaCodesEndpointJaTranslation() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .body("[0].translations.ja", nullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaGBItTranslation() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .body("translations.it", notNullValue());
    }
}