package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private static String base;

    @BeforeClass
    public static void setUp() {
        String env = System.getenv("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        base = env;
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_returns_increasing() {
        given().when().get(base + "/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        Assert.assertEquals("increasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_returns_decreasing() {
        given().when().get(base + "/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        Assert.assertEquals("decreasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_short_length_returns_unordered() {
        given().when().get(base + "/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/a/bbbbb/ccccc/ddddd");
        Assert.assertEquals("unordered", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_equal_within_bounds_returns_unordered() {
        given().when().get(base + "/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/ordered4/apple/apple/apple/apple");
        Assert.assertEquals("unordered", resp.asString());
    }
}