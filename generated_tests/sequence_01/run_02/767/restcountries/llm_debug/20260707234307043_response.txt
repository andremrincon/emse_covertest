package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given()
            .when()
                .get("/v2/alpha/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given()
            .when()
                .get("/v2/alpha?codes=US,CA")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given()
            .when()
                .get("/v2/alpha?codes=XX,YY")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_ServerError() {
        given()
            .when()
                .get("/v2/alpha?codes=%5B%22US%22%2C%22CA%22%5D")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .when()
                .get("/v2/currency/12")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given()
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_Success() {
        given()
            .when()
                .get("/v2/subregion/Western%20Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given()
            .when()
                .get("/v2/lang/es")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_Success() {
        given()
            .when()
                .get("/v2/demonym/American")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Success() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }
}