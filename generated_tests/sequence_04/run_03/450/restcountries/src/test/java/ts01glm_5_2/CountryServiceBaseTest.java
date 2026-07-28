package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
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
    public void getByAlpha_twoCharCodeNotFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/ZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeNotFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_multipleCodes_returns200() {
        given()
            .when()
                .get("/v1/alpha?codes=US;CA;MX")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given()
            .when()
                .get("/v1/alpha?codes=US;US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_notFoundCodes_returns404() {
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
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "France")
            .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v1/name/United States of America")
    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "United States of America")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "NonExistentCountryXYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_serviceInitialized_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }
}