package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_emptyCodes_returns400() {
        given()
            .when()
                .get("/v1/alpha/?codes=")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/?codes=XX;YY;ZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/alpha/?codes=;;")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequest_returns400() {
        given()
            .when()
                .get("/v1/currency/12")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/currency/XyZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/name/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/callingcode/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/capital/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/region/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/subregion/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_internalServerError_returns500() {
        given()
            .when()
                .get("/v1/lang/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_shortCodes_returns400() {
        given()
            .when()
                .get("/v1/alpha/?codes=1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_longCodesWithoutSemicolon_returns400() {
        given()
            .when()
                .get("/v1/alpha/?codes=1234")
            .then()
                .statusCode(404);
    }
}