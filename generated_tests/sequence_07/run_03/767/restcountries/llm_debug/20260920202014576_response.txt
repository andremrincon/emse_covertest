package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void getByAlphaSuccessWithFields() {
        given()
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaListBadRequest() {
        given()
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaListSuccessWithFields() {
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaListNotFound() {
        given()
            .queryParam("codes", "XX")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrencyBadRequest() {
        given()
        .when()
            .get("/v2/currency/12")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrencySuccessWithFields() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrencyNotFound() {
        given()
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameSuccessWithFields() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeSuccessWithFields() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapitalSuccessWithFields() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/v2/capital/Paris")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBlocSuccessWithFields() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionNotFound() {
        given()
        .when()
            .get("/v2/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegionNotFound() {
        given()
        .when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageNotFound() {
        given()
        .when()
            .get("/v2/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonymNotFound() {
        given()
        .when()
            .get("/v2/demonym/123")
        .then()
            .statusCode(404);
    }
}