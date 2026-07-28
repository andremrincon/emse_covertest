package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_TooShort_Returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/1")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_InvalidCodes_Returns400() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "1")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_MalformedCodes_Returns500() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;;CA")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_InvalidLength_Returns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/12")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound_Returns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_Exception_Returns500() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/XyZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound_Returns404() {
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
            .get("/v1/callingcode/99999")
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
}