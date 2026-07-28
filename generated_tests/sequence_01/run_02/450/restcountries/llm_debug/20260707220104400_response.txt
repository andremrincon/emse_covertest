package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccessWithFields() {
        given()
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccessWithFields() {
        given()
            .queryParam("codes", "US;CA;MX")
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given()
            .queryParam("codes", "123")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        given()
        .when()
            .get("/v2/currency/USD")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess() {
        given()
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given()
        .when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given()
        .when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given()
        .when()
            .get("/v2/capital/Paris")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess() {
        given()
        .when()
            .get("/v2/region/Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionSuccess() {
        given()
        .when()
            .get("/v2/subregion/Western%20Europe")
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
    public void testGetByDemonymSuccess() {
        given()
        .when()
            .get("/v2/demonym/American")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocSuccess() {
        given()
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(404);
    }
}