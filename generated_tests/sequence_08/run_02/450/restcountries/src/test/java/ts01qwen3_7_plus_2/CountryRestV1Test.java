package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/A")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "A")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_ServerError() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US|CA|MX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/12")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_ServerError() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/True")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/subregion/Western Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(200);
    }
}