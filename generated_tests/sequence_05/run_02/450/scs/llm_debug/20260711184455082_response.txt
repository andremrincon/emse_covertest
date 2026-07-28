package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private static final String BASE = System.getProperty("api.base",
            System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void test_increasing_order() {
        given().when().get(BASE + "/api/pat/{txt}", "setup-incr").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        Assert.assertEquals("increasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order() {
        given().when().get(BASE + "/api/pat/{txt}", "setup-decr").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        Assert.assertEquals("decreasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_length_violation() {
        given().when().get(BASE + "/api/text2txt/{word1}/{word2}/{word3}", "setup", "setup", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaa", "bbbbb", "ddddd", "ccccc");
        Assert.assertEquals("unordered", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_mixed_order_valid_lengths() {
        given().when().get(BASE + "/api/costfuns/{i}/{s}", 1, "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "ddddd", "bbbbb");
        Assert.assertEquals("unordered", resp.asString());
    }
}