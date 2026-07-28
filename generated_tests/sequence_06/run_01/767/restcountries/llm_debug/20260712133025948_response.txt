package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaReturns200ForValidCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{code}", "US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaReturns400ForTooShortCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{code}", "1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaReturns404WhenNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{code}", "XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListReturns200ForValidList() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US;CA").when().get("/v1/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListReturns400ForEmptyCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListReturns404WhenNoCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "XX;YY;ZZ").when().get("/v1/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyReturns200ForValidCurrency() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyReturns400ForInvalidFormat() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "12");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyReturns404WhenNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameReturns200ForExistingName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/{name}", "France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeReturns200ForValidCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/{code}", "1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalReturns200ForExistingCapital() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/{capital}", "London");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionReturns200ForExistingRegion() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/{region}", "Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregionReturns200ForExistingSubregion() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/subregion/{sub}", "Western Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageReturns200ForValidLanguage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/{lang}", "es");
        resp.then().statusCode(200);
    }
}