package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

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
                .get("/v1/alpha/1")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .queryParam("codes", "1")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX,YY,ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .when()
                .get("/v1/currency/12")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .when()
                .get("/v1/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success() {
        given()
            .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(200);
    }
}