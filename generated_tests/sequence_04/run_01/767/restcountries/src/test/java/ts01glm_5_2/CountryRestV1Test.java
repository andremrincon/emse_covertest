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
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_InvalidCodes_Returns400() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "123")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFoundCodes_Returns404() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_InvalidCurrency_Returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFoundCurrency_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFoundName_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/callingcode/abc")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/lang/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_ValidCode_Returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_ValidCodes_Returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_ValidCurrency_Returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByName_ValidName_Returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }
}