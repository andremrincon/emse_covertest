package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "bpple", "dpple", "cpple");
        String body = act.getBody().asString();
        assertEquals("increasing", body);
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "dpple", "cpple", "apple", "bpple");
        String body = act.getBody().asString();
        assertEquals("decreasing", body);
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_length_returns_unordered() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ccccc", "ddddd");
        String body = act.getBody().asString();
        assertEquals("unordered", body);
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_non_monotonic_order_returns_unordered() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "dpple", "bpple", "cpple");
        String body = act.getBody().asString();
        assertEquals("unordered", body);
    }
}