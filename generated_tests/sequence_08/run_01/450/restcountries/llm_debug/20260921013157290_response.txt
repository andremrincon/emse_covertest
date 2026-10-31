package ts01qwen3_7_plus_2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("base.url",
            System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));

    @Test(timeout = 60000)
    public void testGetByAlpha_Valid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Invalid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/A")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Valid() {
        given()
            .baseUri(BASE_URL)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Invalid() {
        given()
            .baseUri(BASE_URL)
            .queryParam("codes", "")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .baseUri(BASE_URL)
            .queryParam("codes", "XX;YY")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Valid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Invalid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/currency/US")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Valid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
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
    public void testGetByCallingCode_Valid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Valid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Valid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Valid() {
        given()
            .baseUri(BASE_URL)
            .pathParam("subregion", "Western Europe")
        .when()
            .get("/v1/subregion/{subregion}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Valid() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(200);
    }
}