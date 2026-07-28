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
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlphaWithFieldsReturns200() {
        given()
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaWithShortCodeReturns400() {
        given()
        .when()
            .get("/v2/alpha/A")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaWithUnknownCodeReturns404() {
        given()
        .when()
            .get("/v2/alpha/ZZZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaListWithFieldsReturns200() {
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaListWithoutCodesReturns400() {
        given()
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaListWithUnknownCodesReturns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrencyWithUnknownCurrencyReturns404() {
        given()
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameWithUnknownNameReturns404() {
        given()
        .when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeWithUnknownCodeReturns404() {
        given()
        .when()
            .get("/v2/callingcode/99999")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalWithUnknownCapitalReturns404() {
        given()
        .when()
            .get("/v2/capital/12345")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionWithUnknownRegionReturns404() {
        given()
        .when()
            .get("/v2/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegionWithUnknownSubRegionReturns404() {
        given()
        .when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageWithUnknownLanguageReturns404() {
        given()
        .when()
            .get("/v2/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonymWithUnknownDemonymReturns404() {
        given()
        .when()
            .get("/v2/demonym/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBlocWithUnknownBlocReturns404() {
        given()
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}