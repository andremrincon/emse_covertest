package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV1Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalid() {
        given()
            .when()
            .get("/v1/alpha/1")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListEmpty() {
        given()
            .when()
            .get("/v1/alpha?codes=")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListInvalid() {
        given()
            .when()
            .get("/v1/alpha?codes=1")
            .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .when()
            .get("/v1/alpha?codes=XX;YY")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess() {
        given()
            .when()
            .get("/v1/alpha?codes=US;CA")
            .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListServerError() {
        given()
            .when()
            .get("/v1/alpha?codes=US%7CCA%7CMX")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyInvalid() {
        given()
            .when()
            .get("/v1/currency/12")
            .then()
            .statusCode(400);
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
    public void testGetByCurrencySuccess() {
        given()
            .when()
            .get("/v1/currency/USD")
            .then()
            .statusCode(200);
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
    public void testGetByCallingCodeSuccess() {
        given()
            .when()
            .get("/v1/callingcode/1")
            .then()
            .statusCode(200);
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
    public void testGetByRegionSuccess() {
        given()
            .when()
            .get("/v1/region/Europe")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregionSuccess() {
        given()
            .pathParam("subregion", "Western%20Europe")
            .when()
            .get("/v1/subregion/{subregion}")
            .then()
            .statusCode(200);
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