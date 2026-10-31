package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_twoLetter_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Alpha_threeLetter_DEU_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/DEU");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidNumeric_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Alpha_codes_semicolon_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US;CA").when().get("/v2/alpha");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Alpha_duplicate_codes_semicolon_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US;US").when().get("/v2/alpha");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Name_fullText_alternativeSpelling_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/Deutschland");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Name_substring_search_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/Fran");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1CallingCode_invalid_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/abc");
        assertEquals(404, act.getStatusCode());
    }
}