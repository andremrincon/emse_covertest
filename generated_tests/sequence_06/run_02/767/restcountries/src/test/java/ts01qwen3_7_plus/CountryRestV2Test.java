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
    public void testGetByAlphaInvalidLength() {
        given()
            .when()
                .get("/v2/alpha/A")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListInvalidFormat() {
        given()
            .queryParam("codes", "A")
            .when()
                .get("/v2/alpha/")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v2/alpha/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyInvalidLength() {
        given()
            .when()
                .get("/v2/currency/US")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given()
            .when()
                .get("/v2/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given()
            .when()
                .get("/v2/name/NonExistentCountry123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given()
            .when()
                .get("/v2/callingcode/999999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given()
            .when()
                .get("/v2/capital/NonExistentCapital123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given()
            .when()
                .get("/v2/region/NonExistentRegion123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        given()
            .when()
                .get("/v2/subregion/NonExistentSubRegion123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
            .when()
                .get("/v2/lang/xyz123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymNotFound() {
        given()
            .when()
                .get("/v2/demonym/NonExistentDemonym123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
            .when()
                .get("/v2/regionalbloc/XYZ123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountryWithFields() {
        given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testParsedCountriesWithFields() {
        given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }
}