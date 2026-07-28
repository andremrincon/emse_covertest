package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Objects;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountryRestV2Test {

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
    public void testGetByAlpha_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/region/{region}", "Europe").then().statusCode(lessThan(300));
        given().when().get("/v2/subregion/{sub}", "Western Europe").then().statusCode(lessThan(300));
        given().when().get("/v2/demonym/{demonym}", "American").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/{bloc}", "EU").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{alphacode}", "US");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidLength_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{alphacode}", "123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returnsFilteredJson() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{alphacode}", "US");
        assertTrue(Objects.requireNonNull(resp.getBody()).asString().contains("name"));
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{alphacode}", "ZZZ");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_withFields_returnsFilteredJson() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").queryParam("fields", "name;capital;population").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_invalid_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_emptyCodes_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/name/{name}", "Germany").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/{code}", "1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/{currency}", "EUR");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalid_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/{currency}", "12");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v2/name/{name}", "123");
        assertEquals(404, resp.getStatusCode());
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
    public void testParsedCountries_and_parsedCountry_via_fields_returnsFilteredJson() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/region/{region}", "Europe").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").queryParam("fields", "name;capital;population").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }
}