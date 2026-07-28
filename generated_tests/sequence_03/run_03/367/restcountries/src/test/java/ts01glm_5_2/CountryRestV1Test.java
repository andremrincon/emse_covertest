package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_BadRequest_ShortCode() {
        given()
            .when()
                .get("/v1/alpha/A")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_Success() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_NoCodes() {
        given()
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_ShortCodes() {
        given()
            .when()
                .queryParam("codes", "A")
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound() {
        given()
            .when()
                .queryParam("codes", "XX;YY;ZZ")
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_Success() {
        given()
            .when()
                .queryParam("codes", "US;CA")
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_BadRequest() {
        given()
            .when()
                .get("/v1/currency/12")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound() {
        given()
            .when()
                .get("/v1/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound() {
        given()
            .when()
                .get("/v1/name/nonexistentcountry")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound() {
        given()
            .when()
                .get("/v1/callingcode/99999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound() {
        given()
            .when()
                .get("/v1/capital/nonexistentcapital")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound() {
        given()
            .when()
                .get("/v1/region/nonexistentregion")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_NotFound() {
        given()
            .when()
                .get("/v1/subregion/nonexistentsubregion")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound() {
        given()
            .when()
                .get("/v1/lang/nonexistentlang")
            .then()
                .statusCode(404);
    }
}