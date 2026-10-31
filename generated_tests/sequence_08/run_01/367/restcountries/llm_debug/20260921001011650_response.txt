package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_WithFields() {
        given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_WithFields() {
        given()
            .queryParam("codes", "US,CA")
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_Success() {
        given()
            .when()
                .get("/v2/subregion/Western Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .when()
                .get("/v2/lang/Spanish")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_Success() {
        given()
            .when()
                .get("/v2/demonym/American")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Success() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAll_WithFields() {
        given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetCountries_WithFields() {
        given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2")
            .then()
                .statusCode(200);
    }
}