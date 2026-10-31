package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_singleTxtEndpoint_status200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_patOnlyFound_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxbananayy", "banana");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_reverseOnlyFound_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxcbayy", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_patThenReverseAdjacent_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxabccbayy", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_reverseThenPatAdjacent_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxcbaabcyy", "abc");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_noOccurrences_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abcdefgh", "xyz");
        assertEquals(200, resp.getStatusCode());
    }
}