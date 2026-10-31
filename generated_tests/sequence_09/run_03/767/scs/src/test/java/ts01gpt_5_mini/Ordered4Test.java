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
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        assertEquals("increasing", r.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        assertEquals("decreasing", r.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void test_inrange_nonmonotonic_returns_unordered() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd");
        assertEquals("unordered", r.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void test_outofrange_length_returns_unordered() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "abcd", "bbbbb", "ccccc");
        assertEquals("unordered", r.getBody().asString().trim());
    }
}