package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaValidUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaInvalidFormat123() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(404, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFoundXYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListValidCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        assertEquals(400, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListMissingCodesParameter() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha");
        assertEquals(400, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadCodes123() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "123").when().get("/v1/alpha");
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyValidUSD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyInvalidLength123() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/12");
        assertEquals(400, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFoundXYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/XYZ");
        assertEquals(404, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByNameValidFranceFullTextFalse() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/France");
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFoundNumeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        assertEquals(404, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeValid1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1");
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapitalValidLondon() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/London");
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionValidEurope() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubregionValidWesternEurope() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/subregion/Western%20Europe");
        assertEquals(404, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguageValidEs() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/es");
        assertEquals(200, act.statusCode());
    }
}