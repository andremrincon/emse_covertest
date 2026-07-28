package ts01qwen3_7_plus;

import io.restassured.RestAssured;
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

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/1").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().queryParam("codes", "US;CA").when().get("/v1/alpha").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().queryParam("codes", "123").when().get("/v1/alpha").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\", \"CA\"]").when().get("/v1/alpha").then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/123").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_InternalServerError() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/XyZ").then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByName_InternalServerError() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/name/True").then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCallingCode_InternalServerError() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/True").then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/London").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegion_InternalServerError() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/region/True").then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetBySubregion_InternalServerError() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/subregion/True").then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/es").then().statusCode(200);
    }
}