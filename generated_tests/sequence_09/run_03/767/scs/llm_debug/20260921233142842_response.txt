package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL",
                (System.getenv("API_BASE_URL") != null ? System.getenv("API_BASE_URL") : "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "zzzzz", "ccccc");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_200() throws Exception {
        String encoded = URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8").replace("+", "%20");
        given().when().get("/api/pat/{txt}", encoded).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_length_returns_200() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaa", "bbbbb", "ccccc", "ddddd");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_non_monotonic_order_returns_200() {
        given().when().get("/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd");
        assertEquals(200, act.getStatusCode());
    }
}