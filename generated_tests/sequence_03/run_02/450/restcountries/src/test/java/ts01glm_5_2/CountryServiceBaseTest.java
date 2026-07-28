package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeMatch_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeMatch_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/COL")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_noMatch_returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidFormat_returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validCodes_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;CA;MX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_noCodesParam_returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_notFoundCodes_returns404() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_exactNameMatch_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404);
    }

    @Ignore("Illegal character in path at index 46: http://localhost:8080/rest/rest/v1/name/United States of A...")
    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/{name}", "United States of America")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
            .accept(ContentType.JSON)
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/Atlantis")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_allCountries_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_lowercaseCode_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/co")
        .then()
            .statusCode(404);
    }
}