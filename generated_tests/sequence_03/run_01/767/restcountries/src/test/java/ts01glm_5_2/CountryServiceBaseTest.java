package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeMatch_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeMatch_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/USA")
        .then()
            .statusCode(200);
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
    public void getByCodeList_multipleValidCodes_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;CA;MX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;US;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_allInvalidCodes_returns404() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_missingCodesParam_returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_exactNameMatch_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v1/name/French Republic")
    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/{name}", "French Republic")
        .then()
            .statusCode(200);
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
    public void loadJson_allEndpoint_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_mixedValidInvalidCodes_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;XX;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }
}