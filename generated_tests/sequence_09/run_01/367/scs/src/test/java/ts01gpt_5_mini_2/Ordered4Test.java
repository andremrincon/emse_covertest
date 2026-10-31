package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_SERVER");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_SERVER");
        if (base == null || base.isEmpty()) base = System.getProperty("test.server");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_SERVER_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        assertEquals("increasing", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        assertEquals("decreasing", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_length_violation_returns_unordered() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/ordered4/four/bbbbb/ddddd/ccccc");
        assertEquals("unordered", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_valid_lengths_but_unsorted_returns_unordered() {
        given().when().get("/api/pat/ABABCABAB/ABAB").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/ordered4/mango/peach/orange/lemon");
        assertEquals("unordered", res.getBody().asString());
    }
}