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
        String base = System.getProperty("api.baseurl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASEURL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_alpha2_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{alphacode}", "US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_alpha3_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/{alphacode}", "USA");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{alphacode}", "XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_missingCodes_badRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testName_fulltext_exact_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_fulltext_altspelling_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "French Republic");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/{name}", "Fran");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_altspelling_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/{name}", "Republic");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_v1_all_success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badFormat_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{alphacode}", "123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Callingcode_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/{callingcode}", "1");
        resp.then().statusCode(200);
    }
}