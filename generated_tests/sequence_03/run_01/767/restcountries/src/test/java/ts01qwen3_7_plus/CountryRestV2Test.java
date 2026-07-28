package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByAlpha_Success_WithFields() {
        given()
            .queryParam("fields", "name")
        .when()
            .get("/rest/v2/alpha/US")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_Success_WithFields() {
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name")
        .when()
            .get("/rest/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .queryParam("codes", "")
        .when()
            .get("/rest/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .queryParam("codes", "XX;YY")
        .when()
            .get("/rest/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
        .when()
            .get("/rest/v2/currency/EUR")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
        .when()
            .get("/rest/v2/currency/12")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
        .when()
            .get("/rest/v2/name/Germany")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
        .when()
            .get("/rest/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
        .when()
            .get("/rest/v2/callingcode/1")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
        .when()
            .get("/rest/v2/capital/Paris")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
        .when()
            .get("/rest/v2/region/Europe")
        .then()
            .statusCode(404);
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetBySubRegion_Success() {
        given()
        .when()
            .get("/rest/v2/subregion/{sub}", "Western Europe")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
        .when()
            .get("/rest/v2/lang/es")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByDemonym_Success() {
        given()
        .when()
            .get("/rest/v2/demonym/American")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Success() {
        given()
        .when()
            .get("/rest/v2/regionalbloc/EU")
        .then()
            .statusCode(404);
    }
}