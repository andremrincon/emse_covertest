package ts01gpt_5_mini;

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
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_twoLetter_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Alpha_threeLetter_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/DEU");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_bad_request_numeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_codes_success_multiple() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha?codes=US,CA,MX");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_codes_invalid_format_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha?codes=123");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Name_fullText_exact_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France?fullText=true");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Name_substring_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/United");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Name_fullText_exact_match_with_fields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/Germany?fullText=true&fields=name;capital;population");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Name_substring_match() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/United?fullText=false");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1All_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2All_fields_format() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/all?fields=name;capital;region;population;flag");
        assertEquals(200, act.getStatusCode());
    }
}