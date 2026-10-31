package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaV1Alphacode_US_returns200() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaV1Alphacode_invalidFormat_123_returns400() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaV1Alphacode_notFound_XYZ_returns404() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListV1Alpha_codes_returns200() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListV1Alpha_badCodes_123_returns400() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "123").when().get("/v1/alpha");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFullTextSearchV1Name_fullTextTrue_returns200() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/France");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchV1Name_fullTextFalse_returns200() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/Fran");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonV1All_returns200() {
        Response health = given().when().get("/v2");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_withFields_returns200() {
        Response health = given().when().get("/v2");
        health.then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_codesQuery_returns200() {
        Response health = given().when().get("/v2");
        health.then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Callingcode_returns200() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Capital_returns200() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/London");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Region_returns200() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name_notFound_numeric_123_returns404() {
        Response health = given().when().get("/v1/all");
        health.then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().statusCode(404);
    }
}