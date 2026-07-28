package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    private static final String BASE;
    static {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        BASE = env;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/calc/add/1/1?u=" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        assertEquals("increasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/ABABCABAB?u=" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/ddddd/ccccc/aaaaa/bbbbb");
        assertEquals("decreasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_length_returns_unordered() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/text2txt/The/quick/brown?u=" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/four/bbbbb/ddddd/ccccc");
        assertEquals("unordered", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_when_all_equal_returns_unordered() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/cookie/session-id/abc-123-xyz-789/example.com?u=" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/apple/apple/apple/apple");
        assertEquals("unordered", resp.getBody().asString());
    }
}