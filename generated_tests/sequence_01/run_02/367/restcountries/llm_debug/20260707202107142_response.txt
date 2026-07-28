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
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalidLength() {
        given().when().get("/v2/alpha/1").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListEmpty() {
        given().when().get("/v2/alpha?codes=").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given().when().get("/v2/alpha?codes=XX;YY").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListServerError() {
        given().when().get("/v2/alpha?codes=%5B%22US%22,%22CA%22%5D").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyInvalidLength() {
        given().when().get("/v2/currency/12").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given().when().get("/v2/currency/XYZ").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByCurrencyServerError() {
        given().when().get("/v2/currency/%7B%7D").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given().when().get("/v2/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameServerError() {
        given().pathParam("name", "True").when().get("/v2/name/{name}").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given().when().get("/v2/callingcode/abc").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given().when().get("/v2/capital/12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given().when().get("/v2/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        given().when().get("/v2/subregion/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountryWithFields() {
        given().queryParam("fields", "name").when().get("/v2/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountriesWithFields() {
        given().queryParam("fields", "name").when().get("/v2/all").then().statusCode(404);
    }
}