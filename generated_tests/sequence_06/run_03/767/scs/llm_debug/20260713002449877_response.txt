package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaab", "aaaad", "aaaac");
        assertEquals("increasing", act.asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        assertEquals("decreasing", act.asString());
    }

    @Test(timeout = 60000)
    public void test_length_violation_returns_unordered() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ccccc", "ddddd");
        assertEquals("unordered", act.asString());
    }

    @Test(timeout = 60000)
    public void test_equal_strings_return_unordered() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "apple", "apple");
        assertEquals("unordered", act.asString());
    }
}