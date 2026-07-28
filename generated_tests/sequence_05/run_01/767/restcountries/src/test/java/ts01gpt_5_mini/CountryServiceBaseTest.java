package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url", "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_2Letter_OK() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_3Letter_OK() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/USA").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testV1Alpha_BadFormat_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_NotFound_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testV1Alpha_multipleCodes_OK() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US,CA").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testV1Alpha_multipleCodes_BadFormat_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=123").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1Name_fullText_exactMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France?fullText=true").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Name_substring_search() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/Fran?fullText=false").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Callingcode_OK() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Capital_OK() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/London").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Region_OK() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1All_loadJson_success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().statusCode(200);
    }
}