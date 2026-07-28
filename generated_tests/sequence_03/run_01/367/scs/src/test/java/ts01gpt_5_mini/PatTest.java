package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testShortPatternDoesNotInvokeSearch() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "sometext", "ab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testForwardPatternFoundReturnsOk() {
        given().when().get("/api/pat/{txt}", "arrange1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxxabczzz", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testReversePatternFoundReturnsOk() {
        given().when().get("/api/pat/{txt}", "arrange2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxxcbaqq", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPalindromePatThenReverseAdjacentReturnsOk() {
        given().when().get("/api/pat/{txt}", "arrange3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxabccbayy", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPalindromeReverseThenPatAdjacentReturnsOk() {
        given().when().get("/api/pat/{txt}", "arrange4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxcbaabcyy", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacentReturnsOk() {
        given().when().get("/api/pat/{txt}", "arrange5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abcxxxxyycba", "abc");
        assertEquals(200, resp.getStatusCode());
    }
}