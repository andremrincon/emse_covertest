package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess() {
        given()
            .queryParam("codes", "US;CA")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given()
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX;YY")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given()
            .pathParam("currency", "12")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        given()
            .pathParam("currency", "USD")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given()
            .pathParam("currency", "XYZ")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess() {
        given()
            .pathParam("name", "Germany")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given()
            .pathParam("capital", "Paris")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess() {
        given()
            .pathParam("region", "Europe")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionSuccess() {
        given()
            .pathParam("subregion", "Western%20Europe")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymSuccess() {
        given()
            .pathParam("demonym", "American")
        .when()
            .get("/v2/demonym/{demonym}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocSuccess() {
        given()
            .pathParam("regionalbloc", "EU")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(200);
    }
}