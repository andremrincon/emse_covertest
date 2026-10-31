package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturns200() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "abc", "a");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFindPatternOnlyReturns200() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFindReverseOnlyReturns200() {
        given().when().get("/api/pat/{txt}", "ping2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ZZZCBAZZZ", "ABC");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFollowedByReverseAdjacentReturns200() {
        given().when().get("/api/pat/{txt}", "arrange1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABABBABA", "ABAB");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFollowedByPatternAdjacentReturns200() {
        given().when().get("/api/pat/{txt}", "arrange2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "BABAABAB", "ABAB");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonAdjacentBothOccurrencesReturns200() {
        given().when().get("/api/pat/{txt}", "arrange3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "XABABXXXBABA", "ABAB");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatTxtOnlyEndpointReturns200() {
        given().when().get("/api/pat/{txt}", "arrange4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.");
        act.then().statusCode(200);
    }

}