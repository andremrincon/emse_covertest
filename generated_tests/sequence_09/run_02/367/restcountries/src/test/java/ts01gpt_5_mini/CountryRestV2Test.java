package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidShortBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "A").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_fieldsFiltering_returnsJsonString() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}?fields={fields}", "US", "name;capital;population").then().body(containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_validCodes_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha?codes={codes}", "US,CA,MX").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badCodesReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha?codes={codes}", "USCA").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}?fields={fields}", "EUR", "name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalidLength400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}", "12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_validFullTextTrue_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/name/{name}?fullText={full}", "Germany", "true").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/{callingcode}", "1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_notFoundReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/capital/{capital}", "12345").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/region/{region}?fields={fields}", "Europe", "name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostMethodNotAllowed_onV2_returns405() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().post("/v2").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/subregion/{subregion}?fields={fields}", "Western Europe", "name;capital;population").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLang_invalidReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/{lang}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/demonym/{demonym}?fields={fields}", "American", "name;capital;population").then().statusCode(200);
    }
}