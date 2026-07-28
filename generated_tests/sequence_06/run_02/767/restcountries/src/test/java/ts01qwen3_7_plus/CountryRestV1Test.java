package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;

public class CountryRestV1Test {

    private static final String BASE_URI = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByAlpha_Valid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Invalid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("alphacode", "1")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Valid() {
        given()
            .baseUri(BASE_URI)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Invalid() {
        given()
            .baseUri(BASE_URI)
            .queryParam("codes", "1")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .baseUri(BASE_URI)
            .queryParam("codes", "XX;YY")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Exception() {
        given()
            .baseUri(BASE_URI)
            .queryParam("codes", "US;CA;MX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Invalid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("currency", "12")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Exception() {
        given()
            .baseUri(BASE_URI)
            .pathParam("currency", "XyZ")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Valid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("name", "France")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Valid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("callingcode", "1")
        .when()
            .get("/v1/callingcode/{callingcode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Valid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("capital", "London")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Valid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("region", "Europe")
        .when()
            .get("/v1/region/{region}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Valid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("subregion", "Western%20Europe")
        .when()
            .get("/v1/subregion/{subregion}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Valid() {
        given()
            .baseUri(BASE_URI)
            .pathParam("lang", "es")
        .when()
            .get("/v1/lang/{lang}")
        .then()
            .statusCode(200);
    }
}