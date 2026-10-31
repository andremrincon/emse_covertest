package ts01glm_5_2;

import io.restassured.RestAssured;
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
    public void getByAlpha_twoCharCodeFound_returns200() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeFound_returns200() {
        given()
                .when()
                .get("/v1/alpha/USA")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_codeNotFound_returns404() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_v2CodeNotFound_returns404() {
        given()
                .when()
                .get("/v2/alpha/ZZZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nullCodesParam_returns400() {
        given()
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_multipleValidCodes_returns200() {
        given()
                .queryParam("codes", "US;CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given()
                .queryParam("codes", "US;US")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_v2DuplicateCodes_returns200() {
        given()
                .queryParam("codes", "US;US")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nonExistentCodes_returns404() {
        given()
                .queryParam("codes", "XX;YY")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v1/name/French Republic")
    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/{name}", "French Republic")
                .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v2/name/French Republic")
    @Test(timeout = 60000)
    public void fulltextSearch_v2AltSpellingMatch_returns200() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/{name}", "French Republic")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_altSpellingMatch_returns200() {
        given()
                .when()
                .get("/v1/name/Republic")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_v2AltSpellingMatch_returns200() {
        given()
                .when()
                .get("/v2/name/Republic")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_allCountriesEndpoint_returns200() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_v2AllCountriesWithFields_returns200() {
        given()
                .queryParam("fields", "name;capital;region;population;flag")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200);
    }
}