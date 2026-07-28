package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class Ordered4Test {

    private String base() {
        String env = System.getProperty("api.base");
        if (env != null && !env.isEmpty()) return env;
        String e2 = System.getenv("API_BASE");
        if (e2 != null && !e2.isEmpty()) return e2;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        String base = base();
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        Assert.assertEquals("increasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        String base = base();
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        Assert.assertEquals("decreasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_in_range_but_not_ordered_returns_unordered() {
        String base = base();
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd");
        Assert.assertEquals("unordered", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_out_of_range_length_returns_unordered() {
        String base = base();
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "a", "bbbbb", "ccccc", "ddddd");
        Assert.assertEquals("unordered", resp.getBody().asString());
    }
}