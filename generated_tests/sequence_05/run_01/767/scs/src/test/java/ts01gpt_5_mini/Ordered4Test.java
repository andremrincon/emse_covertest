package ts01gpt_5_mini;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
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
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/{txt}", "setup-increasing").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/pat/{txt}", "setup-decreasing").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_valid_lengths_but_not_ordered_returns_unordered() {
        given().when().get("/api/pat/{txt}", "setup-unordered-valid").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd");
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_invalid_lengths_return_unordered() {
        given().when().get("/api/pat/{txt}", "setup-unordered-invalid").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ddddd", "ccccc");
        act.then().body(equalTo("unordered"));
    }
}