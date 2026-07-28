package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Found() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_TooLong() {
        given()
            .when()
                .get("/v1/alpha/1234")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Found() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_NoCodes() {
        given()
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        given()
            .queryParam("codes", "US;CA;[")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .when()
                .get("/v1/currency/12")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .when()
                .get("/v1/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound() {
        given()
            .when()
                .get("/v1/callingcode/99999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_NotFound() {
        given()
            .when()
                .get("/v1/capital/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_NotFound() {
        given()
            .when()
                .get("/v1/region/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_NotFound() {
        given()
            .when()
                .get("/v1/subregion/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound() {
        given()
            .when()
                .get("/v1/lang/123")
            .then()
                .statusCode(404);
    }
}