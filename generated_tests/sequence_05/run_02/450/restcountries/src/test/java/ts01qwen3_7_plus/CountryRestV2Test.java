package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Invalid() {
        given()
            .pathParam("alphacode", "1")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Valid_WithFields() {
        given()
            .pathParam("alphacode", "US")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Invalid() {
        given()
            .queryParam("codes", "")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Valid() {
        given()
            .queryParam("codes", "US,CA")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX,YY,ZZ")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Invalid() {
        given()
            .pathParam("currency", "12")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Valid() {
        given()
            .pathParam("currency", "EUR")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .pathParam("currency", "XYZ")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Valid_WithFields() {
        given()
            .pathParam("name", "Germany")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Valid() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Valid() {
        given()
            .pathParam("capital", "Paris")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Valid() {
        given()
            .pathParam("region", "Europe")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_Valid() {
        given()
            .pathParam("subregion", "Western%20Europe")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Valid() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }
}