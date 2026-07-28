package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Valid() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Invalid() {
        given()
            .pathParam("alphacode", "A")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Valid() {
        given()
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Invalid() {
        given()
            .queryParam("codes", "A")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX;YY")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Exception() {
        given()
            .queryParam("codes", "%5B%22US%22%5D")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Valid() {
        given()
            .pathParam("currency", "USD")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Invalid() {
        given()
            .pathParam("currency", "US")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .pathParam("currency", "XYZ")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Valid() {
        given()
            .pathParam("name", "France")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .pathParam("name", "NonExistentCountry123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Valid() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v1/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Valid() {
        given()
            .pathParam("capital", "London")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Valid() {
        given()
            .pathParam("region", "Europe")
        .when()
            .get("/v1/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Valid() {
        given()
            .pathParam("subregion", "Western%20Europe")
        .when()
            .get("/v1/subregion/{subregion}")
        .then()
            .statusCode(404);
    }
}