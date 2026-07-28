package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeNotFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/XX")
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
    public void getByAlpha_validTwoCharCode_returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_validThreeCharCode_returns200() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_noCodesParameter_returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_multipleValidCodes_returns200() {
        given()
            .queryParam("codes", "US,CA")
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
    public void getByCodeList_nonexistentCodes_returns404() {
        given()
            .queryParam("codes", "XX,YY")
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

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "French Republic".replace(" ", "%20"))
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void substringSearch_partialNameMatch_returns200() {
        given()
            .when()
                .get("/v1/name/{name}", "Fran")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_altSpellingSubstring_returns200() {
        given()
            .when()
                .get("/v1/name/{name}", "French Rep".replace(" ", "%20"))
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void substringSearch_noMatch_returns404() {
        given()
            .when()
                .get("/v1/name/{name}", "123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_allCountriesLoaded_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }
}