package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_InvalidLength() {
        given()
            .pathParam("alphacode", "A")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NullCodes() {
        given()
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_InvalidLength() {
        given()
            .pathParam("currency", "US")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .pathParam("currency", "XYZ")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .pathParam("name", "NonExistentCountry999")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound() {
        given()
            .pathParam("callingcode", "999999")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_NotFound() {
        given()
            .pathParam("capital", "NonExistentCapital999")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_NotFound() {
        given()
            .pathParam("region", "NonExistentRegion999")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_NotFound() {
        given()
            .pathParam("subregion", "NonExistentSubregion999")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound() {
        given()
            .pathParam("lang", "xyz999")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_NotFound() {
        given()
            .pathParam("demonym", "NonExistentDemonym999")
        .when()
            .get("/v2/demonym/{demonym}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NotFound() {
        given()
            .pathParam("regionalbloc", "NONEXISTENT999")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountry_WithFields() {
        given()
            .pathParam("alphacode", "US")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountries_WithFields() {
        given()
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/all")
        .then()
            .statusCode(404);
    }
}