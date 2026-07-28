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
    public void test_pat_length_le2_returns_zero() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "irrelevanttext", "ab").andReturn();
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_pat_found_only_returns_one() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxabcyy", "abc").andReturn();
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_found_only_returns_two() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxcbayy", "abc").andReturn();
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_both_non_adjacent_returns_index_of_first() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abcxxxcba", "abc").andReturn();
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_pat_then_reverse_adjacent_palindrome_returns_index() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abccba", "abc").andReturn();
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_adjacent_palindrome_returns_index() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "cbaabc", "abc").andReturn();
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_pat_text_endpoint_accessible_returns_200() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(200);
    }
}