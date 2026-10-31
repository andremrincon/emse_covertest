package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("api.base");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        r.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        r.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_valid_lengths_but_unordered_returns_unordered() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "ddddd", "bbbbb");
        r.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_length_constraint_violated_returns_unordered() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaa", "bbbbb", "ccccc", "ddddd");
        r.then().body(equalTo("unordered"));
    }
}