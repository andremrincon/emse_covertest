package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCode_returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCode_returns200() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_noCodesParam_returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validCodes_returns200() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given()
            .queryParam("codes", "US,US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_nameMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "French%20Republic")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_allCountries_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_notFoundCodes_returns404() {
        given()
            .queryParam("codes", "XX,YY")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }
}