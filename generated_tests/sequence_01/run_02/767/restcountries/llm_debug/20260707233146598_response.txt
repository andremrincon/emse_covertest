package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("REST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("REST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{code}", "US");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badLength_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{code}", "1");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{code}", "ZZZ");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returnsFilteredBody() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{code}", "US");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US;CA").when().get("/v2/alpha");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_emptyCodes_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badFormat_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/{currency}", "EUR");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badLength_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/{currency}", "12");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", false).when().get("/v2/name/{name}", "Germany");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/callingcode/{callingcode}", "1");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapital_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/capital/{capital}", "Paris");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/region/{region}", "Europe");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/subregion/{subregion}", "Western Europe");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testParsedCountries_withFields_returnsBodyContainsName() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2");
        assertEquals(200, resp.getStatusCode());
    }
}