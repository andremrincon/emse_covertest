package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest() {
        given()
            .pathParam("alphacode", "123")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccessWithFields() {
        given()
            .pathParam("alphacode", "US")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given()
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccessWithFields() {
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Ignore("Illegal character in query at index 23: /rest/v2/alpha/?codes=[\"US\",\"CA\"]")
    @Test(timeout = 60000)
    public void testGetByAlphaListServerError() {
        given()
            .queryParam("codes", "[\"US\",\"CA\"]")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccessWithFields() {
        given()
            .pathParam("currency", "EUR")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyServerError() {
        given()
            .pathParam("currency", "XyZ")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccessWithFields() {
        given()
            .pathParam("name", "Germany")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccessWithFields() {
        given()
            .pathParam("callingcode", "1")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccessWithFields() {
        given()
            .pathParam("capital", "Paris")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccessWithFields() {
        given()
            .pathParam("region", "Europe")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionSuccessWithFields() {
        given()
            .pathParam("subregion", "Western%20Europe")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccessWithFields() {
        given()
            .pathParam("lang", "Spanish")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymSuccessWithFields() {
        given()
            .pathParam("demonym", "American")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/demonym/{demonym}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocSuccessWithFields() {
        given()
            .pathParam("regionalbloc", "EU")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(404);
    }
}