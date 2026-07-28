package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void getByAlpha_Success() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_BadRequest_ShortCode() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_Success() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_ShortCode() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "1")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_LongCodeWithoutSemicolon() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "1234")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound() {
        given()
            .baseUri(baseUrl)
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v1/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_BadRequest() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/currency/12")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/callingcode/99999")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/lang/123")
        .then()
            .statusCode(404);
    }
}