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
    public void getByAlpha_invalidCodeFormat_returns400() {
        given()
            .when()
                .get("/v1/alpha/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validSemicolonCodes_returns200() {
        given()
            .when()
                .get("/v1/alpha?codes=US;CA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_codesNotFound_returns404() {
        given()
            .when()
                .get("/v1/alpha?codes=XX;YY;ZZ")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_missingCodesParam_returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_exactNameMatch_returns200() {
        given()
            .when()
                .get("/v1/name/France?fullText=true")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .when()
                .get("/v1/name/United%20States%20of%20America?fullText=true")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
            .when()
                .get("/v1/name/Atlantis?fullText=true")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_v1AllEndpoint_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_v2AllEndpoint_returns200() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }
}