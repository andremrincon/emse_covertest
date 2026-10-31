package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("testBaseUrl", System.getProperty("baseUrl", "http://localhost:8080"));
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPat_ShortPatternReturns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "abc", "a");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPat_PatternFoundReturns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPat_ReverseFoundReturns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "xxcbayy", "abc");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPat_PatternThenReverseAdjacent_Returns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "xxabccbayy", "abc");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPat_PatternThenReverseNonAdjacent_Returns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "xxabcmiddlecbayy", "abc");
        assertEquals(200, r.getStatusCode());
    }
}