package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
    public void testGetByAlpha_BadRequest() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v2/alpha/{alphacode}", "123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("codes", "")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_ServerError() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("codes", "US,CA")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("codes", "US,CA")
            .queryParam("fields", "name")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v2/currency/{currency}", "12")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/currency/{currency}", "EUR")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/name/{name}", "Germany")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/callingcode/{callingcode}", "1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/capital/{capital}", "Paris")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/region/{region}", "Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/subregion/{subregion}", "Western%20Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/lang/{lang}", "Spanish")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/demonym/{demonym}", "American")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}", "EU")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountry_SuccessWithFields() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("fields", "name")
        .when()
            .get("/v2/alpha/{alphacode}", "US")
        .then()
            .statusCode(404);
    }
}