package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private final String base = System.getProperty("base.url", System.getenv("BASE_URL")) != null
            ? System.getProperty("base.url", System.getenv("BASE_URL"))
            : "http://localhost:8080";

    @Test(timeout = 60000)
    public void test_increasing_order() {
        given().when().get(base + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        Assert.assertEquals("increasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order() {
        given().when().get(base + "/api/pat/ready").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        Assert.assertEquals("decreasing", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_length() {
        given().when().get(base + "/api/pat/ping").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/tiny/bbbbb/ddddd/ccccc");
        Assert.assertEquals("unordered", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_unordered_due_to_non_strict_order() {
        given().when().get(base + "/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/aaaaa/aaaaa/ccccc/bbbbb");
        Assert.assertEquals("unordered", resp.getBody().asString());
    }
}