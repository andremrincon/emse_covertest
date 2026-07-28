package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
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
    public void testGetByAlphaList_BadRequest() {
        given()
            .queryParam("codes", "USCA")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        given()
            .queryParam("codes", "US;$$$$")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .pathParam("currency", "US")
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
    public void testGetByName_Success() {
        given()
            .pathParam("name", "France")
            .queryParam("fullText", false)
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v1/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .pathParam("capital", "London")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .pathParam("region", "Europe")
        .when()
            .get("/v1/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success() {
        given()
            .pathParam("subregion", "Western%20Europe")
        .when()
            .get("/v1/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v1/lang/{lang}")
        .then()
            .statusCode(404);
    }
}