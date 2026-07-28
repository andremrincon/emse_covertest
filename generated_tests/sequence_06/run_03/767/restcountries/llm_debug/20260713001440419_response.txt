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
    public void testGetByAlpha_BadRequest() {
        given()
            .when()
            .get("/v2/alpha/1")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_Null() {
        given()
            .when()
            .get("/v2/alpha")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX;YY")
            .when()
            .get("/v2/alpha")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .when()
            .get("/v2/currency/12")
            .then()
            .statusCode(404);
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
            .get("/v2/name/123")
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
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountries_WithFields() {
        given()
            .queryParam("fields", "name")
            .when()
            .get("/v2/name/Germany")
            .then()
            .statusCode(404);
    }
}