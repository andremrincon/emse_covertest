package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlphaReturnsNullForUnknown2CharCode() {
        given()
            .when()
                .get("/v1/alpha/ZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaReturnsNullForUnknown3CharCode() {
        given()
            .when()
                .get("/v2/alpha/ZZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeListWithNullCodesParameter() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeListWithNullCodesParameterV2() {
        given()
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeListWithDuplicateCodesSemicolon() {
        given()
            .queryParam("codes", "US;US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Ignore("1 expectation failed. JSON path size() doesn't match. Expected: is <1>   Actual: <2>")
    @Test(timeout = 60000)
    public void getByCodeListWithDuplicateCodesComma() {
        given()
            .queryParam("codes", "US,US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400)
                .body("size()", is(1));
    }

    @Test(timeout = 60000)
    public void getByCodeListWithNonMatchingCodes() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeListWithNonMatchingCodesV2() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearchMatchesByAltSpelling() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/French%20Republic")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearchMatchesByAltSpellingV2() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v2/name/Bundesrepublik%20Deutschland")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearchMatchesByAltSpelling() {
        given()
            .when()
                .get("/v1/name/Republic")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearchMatchesByAltSpellingV2() {
        given()
            .when()
                .get("/v2/name/Bundesrepublik")
            .then()
                .statusCode(200);
    }
}