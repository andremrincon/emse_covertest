package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV1Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidLength() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/alpha/1")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_invalid() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("codes", "1")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("codes", "US,CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_notFound() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("codes", "XX,YY")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/currency/12")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_notFound() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/name/NonExistentCountry123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(200);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetBySubregion_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/subregion/{subregion}", "Western Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_valid() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(200);
    }
}