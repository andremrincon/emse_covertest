package ts01gpt_5_mini_2;

import org.junit.Test;
import java.util.UUID;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static final String BASE;
    static {
        String b = System.getProperty("BASE_URL");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = System.getProperty("TEST_BASE_URL");
        if (b == null || b.isEmpty()) b = System.getenv("TEST_BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        resp.then().assertThat().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        resp.then().assertThat().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_length_returns_unordered() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/four/bbbbb/ccccc/ddddd");
        resp.then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void test_unordered_same_lengths_but_not_monotonic_returns_unordered() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/ordered4/aaaaa/ccccc/ddddd/bbbbb");
        resp.then().assertThat().body(equalTo("unordered"));
    }
}