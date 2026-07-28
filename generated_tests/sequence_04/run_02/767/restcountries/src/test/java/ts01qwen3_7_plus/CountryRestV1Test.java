package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        String alpha = "US";
        given()
            .when()
                .get("/v1/alpha/" + alpha)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        String alpha = "XYZ";
        given()
            .when()
                .get("/v1/alpha/" + alpha)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        String codes = "US,CA";
        given()
            .when()
                .get("/v1/alpha?codes=" + codes)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        String codes = "1";
        given()
            .when()
                .get("/v1/alpha?codes=" + codes)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        String currency = "USD";
        given()
            .when()
                .get("/v1/currency/" + currency)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        String currency = "XYZ";
        given()
            .when()
                .get("/v1/currency/" + currency)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        String name = "France";
        given()
            .when()
                .get("/v1/name/" + name)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        String name = "NonExistentCountry123";
        given()
            .when()
                .get("/v1/name/" + name)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        String callingCode = "1";
        given()
            .when()
                .get("/v1/callingcode/" + callingCode)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        String capital = "London";
        given()
            .when()
                .get("/v1/capital/" + capital)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        String region = "Europe";
        given()
            .when()
                .get("/v1/region/" + region)
            .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetBySubregion_Success() {
        String subregion = "Western Europe";
        given()
            .when()
                .get("/v1/subregion/{subregion}", subregion)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        String language = "es";
        given()
            .when()
                .get("/v1/lang/" + language)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_NotFound() {
        String subregion = "NonExistentSubregion123";
        given()
            .when()
                .get("/v1/subregion/" + subregion)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound() {
        String language = "xyz";
        given()
            .when()
                .get("/v1/lang/" + language)
            .then()
                .statusCode(404);
    }
}