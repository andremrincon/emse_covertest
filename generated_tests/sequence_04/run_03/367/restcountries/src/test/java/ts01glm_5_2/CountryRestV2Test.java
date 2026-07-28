package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlphaWithFields() {
        given()
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/US")
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

    @Test(timeout = 60000)
    public void testGetByAlphaListWithFields() {
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given()
            .queryParam("codes", "1234")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given()
        .when()
            .get("/v2/currency/12")
        .then()
            .statusCode(404);
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
            .get("/v2/name/123")
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
            .get("/v2/capital/12345")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given()
        .when()
            .get("/v2/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        given()
        .when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
        .when()
            .get("/v2/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymNotFound() {
        given()
        .when()
            .get("/v2/demonym/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}