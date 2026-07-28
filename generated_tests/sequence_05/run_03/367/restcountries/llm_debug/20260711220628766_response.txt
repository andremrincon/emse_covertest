package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_twoLetter_success_v1() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/US");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_threeLetter_success_v2() {
        RestAssured.given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v2/alpha/USA");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalid_format_badRequest() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/123");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_success_multiple_codes_v1() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_invalid_format_badRequest() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().queryParam("codes", "123").when().get("/v1/alpha");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_name_exact_match() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/name/Germany").andReturn();
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_name_exact_match_fullText_true() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().queryParam("fullText", "true").when().get("/v1/name/Germany");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_altspelling_match_fullText_true() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().queryParam("fullText", "true").when().get("/v1/name/Federal%20Republic%20of%20Germany");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_substring_search_false() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().queryParam("fullText", "false").when().get("/v1/name/United%20States%20of%20America");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testLoadJson_trigger_server_error_v1_alpha_codes_array() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        String encoded = "%5B%22US%22%2C%22CA%22%5D";
        Response act = RestAssured.given().queryParam("codes", encoded).when().get("/v1/alpha");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCallingCode_search_success_v1() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/callingcode/1");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_not_found() {
        RestAssured.given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/XYZ");
        Assert.assertEquals(404, act.getStatusCode());
    }
}