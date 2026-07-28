package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        String w = "abcde";
        String x = "bcdef";
        String y = "cdefg";
        String z = "defgh";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
        String w = "zzzzz";
        String x = "yyyyy";
        String y = "xxxxx";
        String z = "wwwww";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_length_violation_returns_unordered() {
        given().when().get("/api/pat/{txt}", "healthcheck3").then().statusCode(lessThan(300));
        String w = "abcd";
        String x = "bcdef";
        String y = "cdefg";
        String z = "defgh";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_lengths_ok_but_not_monotonic_returns_unordered() {
        given().when().get("/api/pat/{txt}", "healthcheck4").then().statusCode(lessThan(300));
        String w = "aaaaa";
        String x = "ccccc";
        String y = "bbbbb";
        String z = "ddddd";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("unordered"));
    }
}