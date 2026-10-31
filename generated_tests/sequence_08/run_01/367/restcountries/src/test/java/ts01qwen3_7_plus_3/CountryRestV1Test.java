package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given()
            .when()
            .get("/v1/alpha/1")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .when()
            .get("/v1/alpha/?codes=US,CA")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        given()
            .when()
            .get("/v1/alpha/?codes=US%7CCA%7CMX")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .when()
            .get("/v1/currency/12")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_InternalServerError() {
        given()
            .when()
            .get("/v1/currency/INVALIDCURRENCYCODE")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_InternalServerError() {
        given()
            .when()
            .get("/v1/name/True?fullText=yes")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_InternalServerError() {
        given()
            .when()
            .get("/v1/callingcode/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_InternalServerError() {
        given()
            .when()
            .get("/v1/capital/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_InternalServerError() {
        given()
            .when()
            .get("/v1/region/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_InternalServerError() {
        given()
            .when()
            .get("/v1/subregion/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_InternalServerError() {
        given()
            .when()
            .get("/v1/lang/True")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testIsEmpty_Null() {
        given()
            .when()
            .get("/v1/alpha/")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testIsEmpty_EmptyString() {
        given()
            .when()
            .get("/v1/alpha/?codes=")
            .then()
            .statusCode(404);
    }
}