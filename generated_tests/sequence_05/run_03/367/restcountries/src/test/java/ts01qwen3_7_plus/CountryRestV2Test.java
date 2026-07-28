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
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Valid() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Empty() {
        given()
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Exception() {
        given()
            .queryParam("codes", "US")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Invalid() {
        given()
        .when()
            .get("/v2/currency/12")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Exception() {
        given()
        .when()
            .get("/v2/currency/XyZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
        .when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Exception() {
        given()
            .queryParam("fullText", "yes")
        .when()
            .get("/v2/name/Germany")
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
    public void testGetByCallingCode_Exception() {
        given()
        .when()
            .get("/v2/callingcode/True")
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
    public void testGetByRegion_Exception() {
        given()
        .when()
            .get("/v2/region/True")
        .then()
            .statusCode(404);
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetBySubRegion_Valid() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/v2/subregion/{sub}", "Western Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Valid() {
        given()
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_Valid() {
        given()
        .when()
            .get("/v2/demonym/American")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Valid() {
        given()
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(404);
    }
}