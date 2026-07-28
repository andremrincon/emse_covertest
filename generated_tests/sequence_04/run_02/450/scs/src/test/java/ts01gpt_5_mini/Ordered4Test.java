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
    public static void setUp() {
        String base = System.getProperty("apiUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        String w = "aaaaa";
        String x = "bbbbb";
        String y = "ccccc";
        String z = "ddddd";
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        String w = "ddddd";
        String x = "ccccc";
        String y = "bbbbb";
        String z = "aaaaa";
        given().when().get("/api/pat/{txt}", "HelloWorld").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_unordered_values_within_length_bounds_returns_unordered() {
        String w = "aaaaa";
        String x = "ccccc";
        String y = "bbbbb";
        String z = "ddddd";
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_unordered_when_length_out_of_range_returns_unordered() {
        String w = "four";
        String x = "ccccc";
        String y = "ddddd";
        String z = "eeeee";
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        act.then().body(equalTo("unordered"));
    }
}