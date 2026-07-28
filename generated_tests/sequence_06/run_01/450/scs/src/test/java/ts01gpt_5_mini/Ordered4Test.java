package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String w = "aaaaa";
        String x = "bbbbb";
        String y = "ccccc";
        String z = "ddddd";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("increasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String w = "ddddd";
        String x = "ccccc";
        String y = "bbbbb";
        String z = "aaaaa";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("decreasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLengthOutOfRange() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String w = "abcd";
        String x = "bbbbb";
        String y = "ccccc";
        String z = "ddddd";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToNonMonotonicOrder() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        String w = "aaaaa";
        String x = "ccccc";
        String y = "bbbbb";
        String z = "ddddd";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", resp.asString());
    }
}