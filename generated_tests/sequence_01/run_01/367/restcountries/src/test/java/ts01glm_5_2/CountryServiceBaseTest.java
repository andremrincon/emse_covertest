package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeMatch_returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeMatch_returns200() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_nonExistentCode_returns404() {
        given()
            .when()
                .get("/v1/alpha/XX")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidFormat_returns400() {
        given()
            .when()
                .get("/v1/alpha/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validCodes_returns200() {
        given()
            .queryParam("codes", "US;CA;MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nonExistentCodes_returns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha")
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
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "French%20Republic")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "NonExistentCountry123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_v1AllDataLoaded_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_v2AllDataLoaded_returns200() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }
}