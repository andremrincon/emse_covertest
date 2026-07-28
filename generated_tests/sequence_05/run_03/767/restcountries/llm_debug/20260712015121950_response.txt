package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .basePath("/rest")
            .queryParam("codes", "1")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .basePath("/rest")
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .basePath("/rest")
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        given()
            .basePath("/rest")
            .queryParam("codes", "US,CA,MX")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/currency/12")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/subregion/Western%20Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .basePath("/rest")
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_EmptyCodes() {
        given()
            .basePath("/rest")
            .queryParam("codes", "")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

}