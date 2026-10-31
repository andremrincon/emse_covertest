package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        String txt = "abc";
        String pat = "ab";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        String txt = "abcxyz";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        String txt = "cbaxyz";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevImmediatelyAfter() {
        String txt = "abccba";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevLater() {
        String txt = "abcxyzcba";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatImmediatelyAfter() {
        String txt = "cbaabc";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatLater() {
        String txt = "cbaxyzabc";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFirstCharMatchButNotFull() {
        String txt = "abxabc";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFirstCharMatchButNotFull() {
        String txt = "cbxabc";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().statusCode(200);
    }
}