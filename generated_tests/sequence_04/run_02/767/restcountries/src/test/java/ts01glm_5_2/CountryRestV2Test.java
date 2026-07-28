package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaWithFields() {
        given()
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        when()
            .get("/v2/alpha/ZZZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListWithFields() {
        given()
            .queryParam("codes", "US;CA;MX")
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given()
            .queryParam("codes", "1234")
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
    public void testGetByCurrencyBadRequest() {
        when()
            .get("/v2/currency/1234")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        when()
            .get("/v2/callingcode/abc")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        when()
            .get("/v2/capital/12345")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        when()
            .get("/v2/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        when()
            .get("/v2/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymNotFound() {
        when()
            .get("/v2/demonym/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}