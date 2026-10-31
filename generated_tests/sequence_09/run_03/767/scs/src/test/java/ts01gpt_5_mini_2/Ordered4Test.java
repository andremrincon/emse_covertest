package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE", "http://localhost:8080");
        }
        baseURI = base;
        basePath = "";
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProducesIncreasing() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        assertEquals("increasing", act.asString());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProducesDecreasing() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        assertEquals("decreasing", act.asString());
    }

    @Test(timeout = 60000)
    public void testInRangeButNotOrderedProducesUnordered() {
        given().when().get("/api/pat/{txt}", "warm").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd");
        assertEquals("unordered", act.asString());
    }

    @Test(timeout = 60000)
    public void testOutOfRangeLengthProducesUnordered() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "short", "bbbbb", "ccccc", "y");
        assertEquals("unordered", act.asString());
    }
}