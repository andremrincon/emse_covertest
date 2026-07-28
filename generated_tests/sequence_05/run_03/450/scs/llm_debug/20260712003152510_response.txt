package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_returns_increasing_body() {
        given().when().get("/api/pat/{txt}", "arrange-incr").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaab", "aaaad", "aaaac");
        assertEquals("increasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_returns_decreasing_body() {
        given().when().get("/api/pat/{txt}", "arrange-decr").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        assertEquals("decreasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_length_violation_returns_unordered_body() {
        given().when().get("/api/pat/{txt}", "arrange-short").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "a", "b", "d", "c");
        assertEquals("unordered", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_equal_strings_returns_unordered_body() {
        given().when().get("/api/pat/{txt}", "arrange-equal").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaaa", "aaaaa", "aaaaa");
        assertEquals("unordered", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_valid_example_returns_status_200() {
        given().when().get("/api/pat/{txt}", "arrange-status200").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zebra", "yak", "x-ray", "wolf");
        assertEquals(200, resp.getStatusCode());
    }
}