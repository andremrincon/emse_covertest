package ts01gpt_5_mini;

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
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_increasing_order_returns_increasing() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/apple/berry/delta/cider");
        assertEquals("increasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_order_returns_decreasing() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/delta/cider/apple/berry");
        assertEquals("decreasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_all_equal_strings_return_unordered() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/apple/apple/apple/apple");
        assertEquals("unordered", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_length_out_of_bounds_returns_unordered() {
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/four/berry/delta/cider");
        assertEquals("unordered", act.getBody().asString());
    }
}