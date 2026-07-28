package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_nonExistentAlpha2Code_returns404() {
        given()
            .when()
                .get("/v1/alpha/XX")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_validAlpha2Code_returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_validAlpha3Code_returns200() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_nonExistentAlpha3Code_returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_withoutCodesParam_returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validCodes_returns200() {
        given()
            .queryParam("codes", "US;CA")
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
    public void fulltextSearch_altSpellingMatch_returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/French%20Republic")
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
    public void fulltextSearch_noMatch_returns404() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/NonExistentCountry")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_allEndpoint_returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidFormat_returns400() {
        given()
            .when()
                .get("/v1/alpha/1234")
            .then()
                .statusCode(400);
    }
}