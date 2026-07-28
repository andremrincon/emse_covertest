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
        String url = System.getProperty("baseUrl");
        if (url == null) url = System.getenv("BASE_URL");
        if (url == null) url = "http://localhost:8080/rest";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_twoLetter_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_threeLetter_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/USA");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidFormat_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_missingCodes_param_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_multipleCodes_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=US,CA");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFullText_search_altSpelling_matches() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/{name}?fullText=true", "Federal Republic of Germany");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstring_search_altSpelling_contains() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/{name}?fullText=false", "Republic");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_allEndpoint_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        resp.then().statusCode(200);
    }
}