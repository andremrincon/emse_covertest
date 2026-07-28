package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess() {
        Response response = given()
            .when()
                .get("/v2/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest() {
        Response response = given()
            .when()
                .get("/v2/alpha/1");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        Response response = given()
            .when()
                .get("/v2/alpha/ZZZ");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaWithFields() {
        Response response = given()
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess() {
        Response response = given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v2/alpha/");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        Response response = given()
            .queryParam("codes", "1")
            .when()
                .get("/v2/alpha/");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        Response response = given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v2/alpha/");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListWithFields() {
        Response response = given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name;capital")
            .when()
                .get("/v2/alpha/");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        Response response = given()
            .when()
                .get("/v2/currency/EUR");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        Response response = given()
            .when()
                .get("/v2/currency/12");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        Response response = given()
            .when()
                .get("/v2/currency/XYZ");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess() {
        Response response = given()
            .when()
                .get("/v2/name/Germany");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        Response response = given()
            .when()
                .get("/v2/name/123");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        Response response = given()
            .when()
                .get("/v2/callingcode/1");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        Response response = given()
            .when()
                .get("/v2/capital/Paris");
        response.then().statusCode(200);
    }
}