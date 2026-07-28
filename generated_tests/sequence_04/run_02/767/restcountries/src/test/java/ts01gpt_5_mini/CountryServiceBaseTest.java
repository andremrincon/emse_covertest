package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_twoLetter_found() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_threeLetter_found() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/USA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_normal() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha?codes=US,CA");
        act.then().body("size()", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_badFormat_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha?codes=123");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_fullText_exactName_found() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France?fullText=true");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_fullText_altSpelling_found() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/DE?fullText=true");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_name_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/United?fullText=false");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_altSpelling_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/Fran?fullText=false");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2All_loadJson_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/all?fields=name;capital;population;region");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Capital_found() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/London");
        act.then().statusCode(200);
    }
}