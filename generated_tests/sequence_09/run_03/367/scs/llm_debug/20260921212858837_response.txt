package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void test_pat_with_short_pattern_returns_ok() {
        given().when().get("/api/pat/{txt}", "sampletext").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "sampletext", "ab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_found_without_reverse_returns_ok() {
        given().when().get("/api/pat/{txt}", "xxpatternyy").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxpatternyy", "pattern").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_found_without_pat_returns_ok() {
        given().when().get("/api/pat/{txt}", "xxcbaYY").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxcbaYY", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_followed_immediately_by_reverse_adjacent_returns_ok() {
        given().when().get("/api/pat/{txt}", "xxabccbayy").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxabccbayy", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_followed_immediately_by_pat_adjacent_returns_ok() {
        given().when().get("/api/pat/{txt}", "xxcbaabcyy").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxcbaabcyy", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_non_adjacent_returns_ok() {
        given().when().get("/api/pat/{txt}", "abcXXXcba").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "abcXXXcba", "abc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_single_txt_endpoint_processes_input_successfully() {
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(200);
    }

}