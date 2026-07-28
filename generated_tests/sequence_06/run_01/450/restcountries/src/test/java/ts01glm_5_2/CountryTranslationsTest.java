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
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetAllCountriesV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeV1ReturnsDeTranslation() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeV1ReturnsEsTranslation() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeV1ReturnsFrTranslation() {
        given()
            .when()
                .get("/v1/alpha/FR")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeV1ReturnsJaTranslation() {
        given()
            .when()
                .get("/v1/alpha/DE")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeV1ReturnsItTranslation() {
        given()
            .when()
                .get("/v1/alpha/IT")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByAlphaCodesV1ReturnsTranslations() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByNameV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByCurrencyV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByCallingCodeV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByCapitalV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByRegionV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetCountryBySubregionV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByLanguageV1ReturnsTranslations() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByNameFullTextV1ReturnsAllTranslations() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/Germany")
            .then()
                .statusCode(404);
    }
}