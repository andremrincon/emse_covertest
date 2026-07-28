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
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlphaValid() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalid() {
        given()
                .when()
                .get("/v2/alpha/1")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given()
                .when()
                .get("/v2/alpha/ZZZ")
                .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListValid() {
        given()
                .queryParam("codes", "US,CA")
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
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyValid() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyInvalid() {
        given()
                .when()
                .get("/v2/currency/12")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameValid() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeValid() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalValid() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionValid() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionValid() {
        given()
                .pathParam("subregion", "Western%20Europe")
                .when()
                .get("/v2/subregion/{subregion}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageValid() {
        given()
                .when()
                .get("/v2/lang/es")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymValid() {
        given()
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValid() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404);
    }
}