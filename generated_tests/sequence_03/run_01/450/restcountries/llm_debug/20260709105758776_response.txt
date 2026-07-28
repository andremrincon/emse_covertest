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
    public void getByAlpha_validCode_returns200() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_codeTooLong_returns400() {
        given()
                .when()
                .get("/v1/alpha/1234")
                .then()
                .statusCode(400);
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
    public void getByAlphaList_validCodes_returns200() {
        given()
                .queryParam("codes", "US;CA")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_codesTooShort_returns400() {
        given()
                .queryParam("codes", "1")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_codesTooLongNoSemicolon_returns400() {
        given()
                .queryParam("codes", "abcd")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_codesNotFound_returns404() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_wrongLength_returns400() {
        given()
                .when()
                .get("/v1/currency/12")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_returns404() {
        given()
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_returns404() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        given()
                .when()
                .get("/v1/callingcode/99999")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_returns404() {
        given()
                .when()
                .get("/v1/capital/XYZNonExistentCapital")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        given()
                .when()
                .get("/v1/region/XYZNonExistentRegion")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_returns404() {
        given()
                .when()
                .get("/v1/subregion/XYZNonExistentSubregion")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        given()
                .when()
                .get("/v1/lang/123")
                .then()
                .statusCode(404);
    }
}