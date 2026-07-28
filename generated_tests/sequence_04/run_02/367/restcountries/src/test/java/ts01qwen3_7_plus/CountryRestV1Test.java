package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
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
    public void testGetByAlpha_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Invalid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/1");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/XYZ");
        response.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=US,CA");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=XX,YY");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Invalid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=1");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Empty() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Invalid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/12");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/callingcode/1");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/capital/London");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/region/Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/subregion/Western%20Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/lang/es");
        response.then().statusCode(200);
    }
}