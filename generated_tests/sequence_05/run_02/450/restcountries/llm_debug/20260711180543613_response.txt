package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private static final String BASE = System.getProperty("api.base", System.getenv("API_BASE")) != null
            ? System.getProperty("api.base", System.getenv("API_BASE"))
            : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_US_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/alpha/{alphacode}", "US");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalid_tooShort_returns400() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/alpha/{alphacode}", "1");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_returns404() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/alpha/{alphacode}", "ZZZ");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returnsFilteredJson_bodyContainsName() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get(BASE + "/v2/alpha/{alphacode}", "US");
        Assert.assertTrue(resp.getBody().asString().contains("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_withFields_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").queryParam("fields", "name;capital;population").when().get(BASE + "/v2/alpha");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_missingCodes_returns400() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/alpha");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_EUR_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/currency/{currency}", "EUR");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badNumeric_returns400() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/currency/{currency}", "123");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get(BASE + "/v2/name/{name}", "Germany");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_1_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v2/callingcode/{callingcode}", "1");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_bodyContainsCapital() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get(BASE + "/v2/capital/{capital}", "Paris");
        Assert.assertTrue(resp.getBody().asString().contains("capital"));
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid_Europe_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get(BASE + "/v2/region/{region}", "Europe");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_valid_WesternEurope_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get(BASE + "/v2/subregion/{subregion}", "Western%20Europe");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_valid_Spanish_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get(BASE + "/v2/lang/{lang}", "Spanish");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_valid_American_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get(BASE + "/v2/demonym/{demonym}", "American");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_valid_EU_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;currencies").when().get(BASE + "/v2/regionalbloc/{regionalbloc}", "EU");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}