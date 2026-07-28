package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/alpha/XYZ")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_BadRequest_TooLong_Returns400() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/alpha/1234")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlpha_ValidCode_Returns200() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_InvalidCodes_Returns400() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("codes", "123")
        .when()
                .get("/v1/alpha/")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound_UnknownCodes_Returns404() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("codes", "XX;YY;ZZ")
        .when()
                .get("/v1/alpha/")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_ValidCodes_Returns200() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("codes", "US;CA")
        .when()
                .get("/v1/alpha/")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_BadRequest_InvalidLength_Returns400() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/currency/12")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound_UnknownCurrency_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/currency/XYZ")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound_UnknownName_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/name/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound_UnknownCode_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/callingcode/99999")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound_UnknownCapital_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/capital/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound_UnknownRegion_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/region/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_NotFound_UnknownSubregion_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/subregion/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound_UnknownLanguage_Returns404() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/v1/lang/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void doPost_ReturnsMethodNotAllowed405() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .post("/v1")
        .then()
                .statusCode(405);
    }
}