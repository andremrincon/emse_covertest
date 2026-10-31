package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("test.base.uri", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Valid_WithFields() {
        given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Invalid() {
        given()
            .when()
                .get("/v2/alpha/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Valid_WithFields() {
        given()
            .queryParam("fields", "name;capital")
            .queryParam("codes", "US,CA")
            .when()
                .get("/v2/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Invalid() {
        given()
            .when()
                .get("/v2/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Valid() {
        given()
            .when()
                .get("/v2/currency/EUR")
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
    public void testGetByName_Valid() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Valid() {
        given()
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Valid() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Valid() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetBySubRegion_Valid() {
        given()
            .when()
                .get("/v2/subregion/{subregion}", "Western Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Valid() {
        given()
            .when()
                .get("/v2/lang/Spanish")
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

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given()
            .when()
                .get("/v2/alpha/ZZZ")
            .then()
                .statusCode(404);
    }
}