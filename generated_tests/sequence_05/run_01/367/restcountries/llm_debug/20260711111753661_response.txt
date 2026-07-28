package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
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
        given()
        .when()
            .get("/v2/alpha/ZZZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListValidWithFields() {
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListInvalid() {
        given()
            .queryParam("codes", "12345")
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
    public void testGetByCurrencyInvalid() {
        given()
        .when()
            .get("/v2/currency/12")
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
            .get("/v2/name/zzznonexistent")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given()
        .when()
            .get("/v2/callingcode/99999")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given()
        .when()
            .get("/v2/capital/zzznonexistent")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given()
        .when()
            .get("/v2/region/zzznonexistent")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        given()
        .when()
            .get("/v2/subregion/zzznonexistent")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
        .when()
            .get("/v2/lang/zzz")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymNotFound() {
        given()
        .when()
            .get("/v2/demonym/zzznonexistent")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
        .when()
            .get("/v2/regionalbloc/zzz")
        .then()
            .statusCode(404);
    }
}