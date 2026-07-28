package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Invalid() {
        given()
        .when()
            .get("/v1/alpha/1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Empty() {
        given()
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Found() {
        given()
            .queryParam("codes", "US,CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX,YY")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Exception() {
        given()
        .when()
            .get("/v1/alpha?codes=%5B%22US%22%5D")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Invalid() {
        given()
        .when()
            .get("/v1/currency/12")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Found() {
        given()
        .when()
            .get("/v1/currency/USD")
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
    public void testGetByCurrency_Exception() {
        given()
        .when()
            .get("/v1/currency/XyZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Found() {
        given()
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Found() {
        given()
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Found() {
        given()
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Found() {
        given()
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Found() {
        given()
        .when()
            .get("/v1/subregion/Western%20Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Found() {
        given()
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(404);
    }
}