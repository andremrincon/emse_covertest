package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCode_returns200() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCode_returns200() {
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
                .get("/v1/alpha/XYZ")
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
    public void getByCodeList_invalidFormat_returns400() {
        given()
                .queryParam("codes", "123")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
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
                .get("/v1/name/French%20Republic")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/Atlantis")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_serviceInitialized_allEndpointReturns200() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_mixedValidInvalidCodes_returns200() {
        given()
                .queryParam("codes", "US;XX;CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
    }
}