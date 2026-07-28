package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private static final String BASE_URL = "http://localhost:8080/rest";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaValid() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalid() {
        given()
            .pathParam("alphacode", "1")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaWithFields() {
        given()
            .pathParam("alphacode", "US")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListValid() {
        given()
            .queryParam("codes", "US;CA")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListInvalid() {
        given()
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyValid() {
        given()
            .pathParam("currency", "EUR")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyInvalid() {
        given()
            .pathParam("currency", "12")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByNameValid() {
        given()
            .pathParam("name", "Germany")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeValid() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalValid() {
        given()
            .pathParam("capital", "Paris")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionValid() {
        given()
            .pathParam("region", "Europe")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionValid() {
        given()
            .pathParam("subregion", "Western Europe".replace(" ", "%20"))
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageValid() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymValid() {
        given()
            .pathParam("demonym", "American")
        .when()
            .get("/v2/demonym/{demonym}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValid() {
        given()
            .pathParam("regionalbloc", "EU")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(200);
    }
}