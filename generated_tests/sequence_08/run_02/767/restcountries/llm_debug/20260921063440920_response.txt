package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha() {
        given()
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList() {
        given()
            .queryParam("codes", "US,CA")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX,YY")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListEmpty() {
        given()
            .queryParam("codes", "")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency() {
        given()
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyError() {
        given()
        .when()
            .get("/v2/currency/%7B%7D")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName() {
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
    public void testGetByCallingCode() {
        given()
        .when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital() {
        given()
        .when()
            .get("/v2/capital/Paris")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion() {
        given()
        .when()
            .get("/v2/region/Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion() {
        given()
        .when()
            .get("/v2/subregion/Western%20Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage() {
        given()
        .when()
            .get("/v2/lang/Spanish")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym() {
        given()
        .when()
            .get("/v2/demonym/American")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc() {
        given()
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }
}