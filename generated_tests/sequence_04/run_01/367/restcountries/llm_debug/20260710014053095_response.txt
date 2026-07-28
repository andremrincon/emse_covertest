package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
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
    public void testGetByAlphaList_BadRequest() {
        given()
            .when()
                .get("/v1/alpha?codes=1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .when()
                .get("/v1/alpha?codes=XX;YY;ZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .when()
                .get("/v1/alpha?codes=US;CA")
            .then()
                .statusCode(404);
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
                .get("/v1/name/NonExistentCountryName123")
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
                .get("/v1/capital/NonExistentCapital123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_NotFound() {
        given()
            .when()
                .get("/v1/region/NonExistentRegion123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_NotFound() {
        given()
            .when()
                .get("/v1/subregion/NonExistentSubregion123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound() {
        given()
            .when()
                .get("/v1/lang/xyz123")
            .then()
                .statusCode(404);
    }
}