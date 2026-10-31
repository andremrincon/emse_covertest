package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_InvalidLength() {
        given()
            .when()
                .get("/v2/alpha/A")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InvalidLength() {
        given()
            .queryParam("codes", "A")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX,YY,ZZ")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_InvalidLength() {
        given()
            .when()
                .get("/v2/currency/EU")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .when()
                .get("/v2/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .when()
                .get("/v2/name/Atlantis")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound() {
        given()
            .when()
                .get("/v2/callingcode/999999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_NotFound() {
        given()
            .when()
                .get("/v2/capital/12345")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_NotFound() {
        given()
            .when()
                .get("/v2/region/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_NotFound() {
        given()
            .when()
                .get("/v2/subregion/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound() {
        given()
            .when()
                .get("/v2/lang/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_NotFound() {
        given()
            .when()
                .get("/v2/demonym/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NotFound() {
        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountry_WithFields() {
        given()
            .queryParam("fields", "name")
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testParsedCountries_WithFields() {
        given()
            .queryParam("fields", "name")
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }
}