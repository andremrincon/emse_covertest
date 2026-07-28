package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest() {
        given()
            .baseUri(baseUrl)
            .pathParam("alphacode", "1")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaWithFields() {
        given()
            .baseUri(baseUrl)
            .pathParam("alphacode", "US")
            .queryParam("fields", "name")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "XX,YY")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given()
            .baseUri(baseUrl)
            .pathParam("currency", "12")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("currency", "XYZ")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyWithFields() {
        given()
            .baseUri(baseUrl)
            .pathParam("currency", "EUR")
            .queryParam("fields", "name")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("name", "Atlantis")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("callingcode", "99999")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("capital", "Atlantis")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("region", "Atlantis")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("subregion", "Atlantis")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("lang", "xyz")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("demonym", "Atlantean")
        .when()
            .get("/v2/demonym/{demonym}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
            .baseUri(baseUrl)
            .pathParam("regionalbloc", "XYZ")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(404);
    }
}