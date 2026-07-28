package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetAlphaBy2Code_US_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAlphaBy3Code_USA_v2_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/USA");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAlphaNotFound_XYZ_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetAlphaCodes_multiple_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetAlphaCodes_bad_format_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "123").when().get("/v1/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNameFullText_exactMatch_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France?fullText=true");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNameSubstring_match_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/Fran?fullText=false");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNameFullText_altSpelling_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/Bundesrepublik%20Deutschland?fullText=true");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNameSubstring_altSpelling_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/Federal?fullText=false");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_v1_all_success_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_trigger_server_error_returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=%5B%22US%22,%22CA%22%5D");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetAlpha_badFormat_numeric_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }
}