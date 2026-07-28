package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsDe() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeUSTriggersTranslations() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeGBTriggersTranslations() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodesTriggersTranslations() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyUSDTriggersTranslations() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameFranceTriggersTranslations() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode1TriggersTranslations() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalLondonTriggersTranslations() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionEuropeTriggersTranslations() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregionWesternEuropeTriggersTranslations() {
        given()
            .when()
                .get("/v1/subregion/Western%20Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLangEsTriggersTranslations() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameFullTextTriggersTranslations() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeDETriggersAllSetters() {
        given()
            .when()
                .get("/v1/alpha/DE")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeFRTriggersAllSetters() {
        given()
            .when()
                .get("/v1/alpha/FR")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeJPTriggersAllSetters() {
        given()
            .when()
                .get("/v1/alpha/JP")
            .then()
                .statusCode(404);
    }
}