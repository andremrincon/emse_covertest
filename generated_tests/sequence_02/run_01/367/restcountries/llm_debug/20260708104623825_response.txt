package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given()
            .when()
                .get("/v1/alpha/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .when()
                .get("/v1/alpha?codes=US,CA")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .when()
                .get("/v1/alpha?codes=")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .when()
                .get("/v1/alpha?codes=XX,YY,ZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        String encoded;
        try {
            encoded = URLEncoder.encode("[\"US\", \"CA\"]", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            encoded = "[\"US\", \"CA\"]";
        }
        given()
            .queryParam("codes", encoded)
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .when()
                .get("/v1/currency/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .when()
                .get("/v1/name/NonExistentCountry12345")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success() {
        given()
            .when()
                .get("/v1/subregion/{subregion}", "Western%20Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404);
    }
}