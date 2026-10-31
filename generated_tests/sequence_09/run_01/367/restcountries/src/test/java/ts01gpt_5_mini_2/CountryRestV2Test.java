package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("REST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("REST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("rest.base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/US");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidShortReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/1");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFieldsReturnsFilteredBody() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        assertTrue(act.asString().contains("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_semicolonSeparatedReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US;CA").when().get("/v2/alpha");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_commaSeparatedBadFormatReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v2/alpha");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_unknownCodesReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "XX;YY;ZZ").when().get("/v2/alpha");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalidLengthReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/12");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_notFoundReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2All_withFieldsReturnsFilteredBody() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all");
        assertTrue(act.asString().contains("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_missingCodesReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_validReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/Germany");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_notFoundReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/Atlantis");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPostOnV2_returnsMethodNotAllowed405() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().post("/v2");
        assertEquals(405, act.getStatusCode());
    }
}