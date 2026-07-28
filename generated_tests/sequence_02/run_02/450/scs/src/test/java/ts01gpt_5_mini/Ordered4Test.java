package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("baseUrl");
        String base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder_returnsIncreasing() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        assertEquals("increasing", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder_returnsDecreasing() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "vvvvv", "xxxxx");
        assertEquals("decreasing", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLength_returnsUnordered() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcd", "bbbbb", "ccccc", "ddddd");
        assertEquals("unordered", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToNonMonotonicOrder_returnsUnordered() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "charm", "delta", "baker");
        assertEquals("unordered", act.getBody().asString().trim());
    }
}