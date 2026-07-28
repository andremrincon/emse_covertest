package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_returns_200() {
        given().when().get("/api/pat/{txt}", "arrange-text").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_decreasing_returns_200() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_length_out_of_range_returns_200() {
        given().when().get("/api/pat/{txt}", "prepare").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcd", "bbbbb", "ddddd", "ccccc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_unordered_within_length_but_not_ordered_returns_200() {
        given().when().get("/api/pat/{txt}", "prime").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "ddddd", "bbbbb").then().statusCode(200);
    }
}