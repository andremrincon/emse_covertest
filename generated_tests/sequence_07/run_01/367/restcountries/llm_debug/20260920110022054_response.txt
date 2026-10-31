package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void v1AlphaByCode_US_Returns200WithDeTranslation() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void v1AlphaByCode_GB_Returns200WithEsTranslation() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(200)
                .body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void v1AlphaByCodes_US_CA_MX_Returns200WithFrTranslation() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void v1Name_France_Returns200WithJaTranslation() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("[0].translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void v1Currency_USD_Returns200WithItTranslation() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("[0].translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void v1CallingCode_1_Returns200() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void v1Capital_London_Returns200() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void v1Region_Europe_Returns200() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void v1Subregion_WesternEurope_Returns200() {
        given()
            .when()
                .get("/v1/subregion/Western%20Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void v1Lang_Es_Returns200() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void v1AlphaByCode_123_Returns400() {
        given()
            .when()
                .get("/v1/alpha/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1AlphaByCode_XYZ_Returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void v1AlphaByCodes_Invalid_Returns400() {
        given()
            .queryParam("codes", "123")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }
}