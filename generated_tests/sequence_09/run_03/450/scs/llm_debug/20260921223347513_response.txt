package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static String BASE;
    private static String PAT_TXT;

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        BASE = base;
        String txt = "The quick brown fox jumps over the lazy dog.";
        try {
            PAT_TXT = URLEncoder.encode(txt, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", PAT_TXT).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaab", "aaaad", "aaaac").then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", PAT_TXT).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx").then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_length_out_of_range_returns_unordered() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", PAT_TXT).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "aaaaa", "aaaaa", "aaaaa").then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_lengths_in_range_but_not_ordered_returns_unordered() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", PAT_TXT).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "ddddd", "bbbbb").then().body(equalTo("unordered"));
    }
}