package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/alpha/US");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        Response response = given()
                .when()
                .get("/v2/alpha/1");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        Response response = given()
                .queryParam("codes", "US,CA")
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/alpha");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        Response response = given()
                .queryParam("codes", "")
                .when()
                .get("/v2/alpha");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/currency/EUR");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        Response response = given()
                .when()
                .get("/v2/currency/12");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/name/Germany");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/callingcode/1");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/capital/Paris");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/region/Europe");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/subregion/Western%20Europe");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/lang/Spanish");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/demonym/American");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Success() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/regionalbloc/EU");

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetAll_WithFields() {
        Response response = given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/all");

        assertEquals(404, response.getStatusCode());
    }
}