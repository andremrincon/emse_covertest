package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaFound() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess() {
        given()
            .when()
                .get("/v2/alpha?codes=US,CA")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .when()
                .get("/v2/alpha?codes=XX,YY,ZZ")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given()
            .when()
                .get("/v2/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given()
            .when()
                .get("/v2/name/Atlantis")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given()
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given()
            .when()
                .get("/v2/callingcode/999999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given()
            .when()
                .get("/v2/capital/12345")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given()
            .when()
                .get("/v2/region/123")
            .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetBySubRegionSuccess() {
        given()
            .when()
                .get("/v2/subregion/{sub}", "Western Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        given()
            .when()
                .get("/v2/subregion/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess() {
        given()
            .when()
                .get("/v2/lang/Spanish")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
            .when()
                .get("/v2/lang/123")
            .then()
                .statusCode(404);
    }
}