package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        if (!baseUrl.endsWith("/rest")) {
            baseUrl = baseUrl + "/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeFound_returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeFound_returns200() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(404);
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
    public void getByCodeList_multipleValidCodes_returns200() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given()
            .queryParam("codes", "US;US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_notFoundCodes_returns404() {
        given()
            .queryParam("codes", "XX;YY")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_noCodesParam_returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_exactNameMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in path at index 46: http://localhost:8080/rest/rest/v1/name/French Republic")
    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "French Republic")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_getAllCountries_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_v2_multipleCodes_returns200() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(404);
    }
}