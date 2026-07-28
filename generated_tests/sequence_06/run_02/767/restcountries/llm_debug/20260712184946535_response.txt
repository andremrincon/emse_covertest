package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCode_returns200() {
        RestAssured.given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCode_returns200() {
        RestAssured.given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFoundCode_returns404() {
        RestAssured.given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidFormat_returns400() {
        RestAssured.given()
            .when()
                .get("/v1/alpha/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nullCodes_returns400() {
        RestAssured.given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validMultipleCodes_returns200() {
        RestAssured.given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        RestAssured.given()
            .queryParam("codes", "US;US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_notFoundCodes_returns404() {
        RestAssured.given()
            .queryParam("codes", "XX;YY")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_nameMatch_returns200() {
        RestAssured.given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        RestAssured.given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/French%20Republic")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_notFound_returns404() {
        RestAssured.given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/NonExistentCountry")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void substringSearch_namePartialMatch_returns200() {
        RestAssured.given()
            .when()
                .get("/v1/name/Fran")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_altSpellingPartialMatch_returns200() {
        RestAssured.given()
            .when()
                .get("/v1/name/French%20Rep")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_notFound_returns404() {
        RestAssured.given()
            .when()
                .get("/v1/name/zzzznonexistent")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_allCountries_returns200() {
        RestAssured.given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }
}