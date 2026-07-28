package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeNotFound_returns404() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/XX")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeNotFound_returns404() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/ZZZ")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeFound_returns200() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeFound_returns200() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha/USA")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nullCodesParam_returns400() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/alpha")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given()
                .contentType("application/json")
                .queryParam("codes", "US;US")
        .when()
                .get("/v1/alpha")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_mixedFoundAndNotFoundCodes_returns200() {
        given()
                .contentType("application/json")
                .queryParam("codes", "US;XX")
        .when()
                .get("/v1/alpha")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_matchByName_returns200() {
        given()
                .contentType("application/json")
                .queryParam("fullText", "true")
        .when()
                .get("/v1/name/France")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_matchByAltSpelling_returns200() {
        given()
                .contentType("application/json")
                .queryParam("fullText", "true")
        .when()
                .get("/v1/name/French%20Republic")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_matchByNameSubstring_returns200() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/name/Franc")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_matchByAltSpellingSubstring_returns200() {
        given()
                .contentType("application/json")
        .when()
                .get("/v1/name/French%20Rep")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_v2AllEndpoint_returns200() {
        given()
                .contentType("application/json")
        .when()
                .get("/v2/all")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_v2AlphaEndpoint_returns200() {
        given()
                .contentType("application/json")
        .when()
                .get("/v2/alpha/US")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_multipleValidCodes_returns200() {
        given()
                .contentType("application/json")
                .queryParam("codes", "US;CA;MX")
        .when()
                .get("/v1/alpha")
        .then()
                .statusCode(200);
    }
}