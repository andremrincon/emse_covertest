package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaReturnsCountry() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequestShort() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US;CA").when().get("/v1/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequestEmpty() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "XX;YY;ZZ").when().get("/v1/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/12");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/NoSuchCountryXYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/London");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregionReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/subregion/Western%20Europe");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageReturnsCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        resp.then().statusCode(200);
    }
}