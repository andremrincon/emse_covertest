package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testShortPattern_Returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        String txt = "abcdefgh";
        String pat = "ab";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundWithoutReverse_Returns200() {
        given().when().get("/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        String pat = "abc";
        String txt = "xxabcyy";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnly_Returns200() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        String pat = "abc";
        String txt = "xxcbazz";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAdjacentPalindrome_Returns200() {
        given().when().get("/api/pat/{txt}", "awake").then().statusCode(lessThan(300));
        String pat = "aba";
        String txt = "abaaba";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonAdjacentBothOccurrences_Returns200() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        String pat = "abc";
        String txt = "abcxxxxcba";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSingleTxtEndpoint_Returns200() {
        given().when().get("/api/pat/{txt}", "Thequickbrownfox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "Thequickbrownfox");
        resp.then().statusCode(200);
    }
}