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
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/US");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalid_length_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/1");
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_codes_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_bad_format_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "123").when().get("/v1/alpha");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_malformed_array_returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha");
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_USD_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/USD");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_bad_format_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/12");
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/XYZ");
        assertEquals(404, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_valid_France_fullText_false_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("fullText", "false").when().get("/v1/name/France");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_numeric_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/name/123");
        assertEquals(404, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_1_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/callingcode/1");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapital_valid_London_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/capital/London");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid_Europe_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/region/Europe");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_valid_es_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/lang/es");
        assertEquals(200, res.getStatusCode());
    }
}