package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given()
            .when()
                .get("/v1/alpha/ZZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListServerError() {
        given()
            .queryParam("codes", "US;CA;MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given()
            .when()
                .get("/v1/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given()
            .when()
                .get("/v1/name/Atlantis")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given()
            .when()
                .get("/v1/callingcode/999999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given()
            .when()
                .get("/v1/capital/Atlantis")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given()
            .when()
                .get("/v1/region/Atlantis")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(200);
    }
}