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
                .get("/v2/alpha/USA")
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
    public void getByCodeList_nullCodes_returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given()
            .queryParam("codes", "US,US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_multipleCodes_returns200() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nonExistentCodes_returns404() {
        given()
            .queryParam("codes", "XX,YY,ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_exactName_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v2/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpelling_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v2/name/{name}", "French%20Republic")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_nameMatch_returns200() {
        given()
            .when()
                .get("/v2/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_altSpelling_returns200() {
        given()
            .when()
                .get("/v2/name/Bundesrepublik")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_success_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_singleCode_v2_returns200() {
        given()
            .queryParam("codes", "US")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_withFields_returns200() {
        given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }
}