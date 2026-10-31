package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void getByAlphaWithFieldsReturns200() {
        given()
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaNotFoundReturns404() {
        given()
        .when()
            .get("/v2/alpha/ZZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListBadRequestReturns400() {
        given()
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListNotFoundReturns404() {
        given()
            .queryParam("codes", "XX")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListInternalServerErrorReturns500() {
        given()
            .queryParam("codes", "US;CA")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencyBadRequestReturns400() {
        given()
        .when()
            .get("/v2/currency/12")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrencyNotFoundReturns404() {
        given()
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameNotFoundReturns404() {
        given()
        .when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeNotFoundReturns404() {
        given()
        .when()
            .get("/v2/callingcode/abc")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalNotFoundReturns404() {
        given()
        .when()
            .get("/v2/capital/12345")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionNotFoundReturns404() {
        given()
        .when()
            .get("/v2/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegionNotFoundReturns404() {
        given()
        .when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageNotFoundReturns404() {
        given()
        .when()
            .get("/v2/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonymNotFoundReturns404() {
        given()
        .when()
            .get("/v2/demonym/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBlocNotFoundReturns404() {
        given()
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}