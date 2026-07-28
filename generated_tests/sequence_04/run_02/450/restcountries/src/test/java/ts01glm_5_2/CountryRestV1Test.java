package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_BadRequest_ShortCode_Returns400() {
        given()
            .when()
                .get("/v1/alpha/1")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlpha_Success_Returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_ShortCode_Returns400() {
        given()
            .queryParam("codes", "1")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound_Returns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_Success_Returns200() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_BadRequest_Returns400() {
        given()
            .when()
                .get("/v1/currency/12")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/callingcode/99999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/capital/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/region/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/subregion/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound_Returns404() {
        given()
            .when()
                .get("/v1/lang/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void doPost_ReturnsMethodNotAllowed() {
        given()
            .when()
                .post("/v1")
            .then()
                .statusCode(405);
    }
}