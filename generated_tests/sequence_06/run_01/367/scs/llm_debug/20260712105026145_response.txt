package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static final String BASE = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void test_increasing_order_results_in_increasing() {
        String setupTxt = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "apple", "basil", "delta", "cello");
        resp.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_results_in_decreasing() {
        String setupTxt = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        resp.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_length_violation_results_in_unordered() {
        String setupTxt = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ccccc", "ddddd");
        resp.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_equal_strings_within_length_results_in_unordered() {
        String setupTxt = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/{txt}", setupTxt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "apple", "apple");
        resp.then().body(equalTo("unordered"));
    }
}