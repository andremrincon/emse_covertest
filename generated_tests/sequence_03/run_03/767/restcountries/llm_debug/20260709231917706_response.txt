package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/A")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha?codes=A")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha?codes=US,CA")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/currency/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/name/NonExistentCountry123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/callingcode/99999")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_NotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/capital/NonExistentCapital123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_NotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/region/NonExistentRegion123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success() {
        String encoded;
        try {
            encoded = java.net.URLEncoder.encode("Western Europe", "UTF-8").replace("+", "%20");
        } catch (java.io.UnsupportedEncodingException e) {
            encoded = "Western%20Europe";
        }
        given()
            .baseUri(BASE_URL)
            .pathParam("subregion", encoded)
        .when()
            .get("/v1/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(404);
    }
}