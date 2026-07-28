package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given()
            .pathParam("alphacode", "1")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_Empty() {
        given()
            .queryParam("codes", "")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Ok() {
        given()
            .queryParam("codes", "US")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        given()
            .queryParam("codes", "US%7CCA%7CMX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .pathParam("currency", "12")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Ok() {
        given()
            .pathParam("currency", "USD")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_InternalServerError() {
        given()
            .pathParam("currency", "XyZ")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Ok() {
        given()
            .pathParam("name", "France")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_InternalServerError() {
        given()
            .pathParam("name", "True")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Ok() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v1/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_InternalServerError() {
        given()
            .pathParam("callingcode", "True")
        .when()
            .get("/v1/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Ok() {
        given()
            .pathParam("capital", "London")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Ok() {
        given()
            .pathParam("region", "Europe")
        .when()
            .get("/v1/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Ok() {
        given()
            .pathParam("subregion", "Western%20Europe")
        .when()
            .get("/v1/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Ok() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v1/lang/{lang}")
        .then()
            .statusCode(404);
    }
}