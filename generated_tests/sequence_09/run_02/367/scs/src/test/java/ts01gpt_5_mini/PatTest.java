package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_shortPattern_returns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "hello", "a");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_foundOnly_returns200() {
        given().when().get("/api/pat/{txt}", "sample").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxxabcyyy", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_reverseOnly_returns200() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxxcba", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_adjacentPalindrome_returns200() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abccba", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_bothNonAdjacent_returns200() {
        given().when().get("/api/pat/{txt}", "init").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abcxxxcba", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_txtOnly_endpoint_returns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.");
        assertEquals(200, resp.getStatusCode());
    }
}