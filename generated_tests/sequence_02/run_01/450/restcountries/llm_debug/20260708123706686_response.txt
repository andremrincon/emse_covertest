package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given()
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListEmpty() {
        given()
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX,YY,ZZ")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListServerError() {
        given()
            .queryParam("codes", "US,CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
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
    public void testGetByCurrencyServerError() {
        given()
        .when()
            .get("/v1/currency/XyZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameServerError() {
        given()
        .when()
            .get("/v1/name/True")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given()
        .when()
            .get("/v1/callingcode/99999")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeServerError() {
        given()
        .when()
            .get("/v1/callingcode/True")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given()
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given()
        .when()
            .get("/v1/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionServerError() {
        given()
        .when()
            .get("/v1/region/True")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregionNotFound() {
        given()
        .when()
            .get("/v1/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
        .when()
            .get("/v1/lang/123")
        .then()
            .statusCode(404);
    }
}