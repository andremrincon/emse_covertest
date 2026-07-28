package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        Map<String, String> env = System.getenv();
        String envBase = env.getOrDefault("API_BASE", null);
        String propBase = System.getProperty("api.base", null);
        if (propBase != null && !propBase.isEmpty()) {
            BASE = propBase;
        } else if (envBase != null && !envBase.isEmpty()) {
            BASE = envBase;
        } else {
            BASE = "http://localhost:8080";
        }
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testOrdered4_increasing_returnsIncreasingBody() {
        given().when().get(BASE + "/api/pat/ping").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        assertEquals("increasing", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testOrdered4_decreasing_returnsDecreasingBody() {
        given().when().get(BASE + "/api/pat/ping2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/ddddd/ccccc/aaaaa/bbbbb");
        assertEquals("decreasing", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testOrdered4_unordered_dueToLengthOutsideRange_returnsUnorderedBody() {
        given().when().get(BASE + "/api/pat/ping3").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/abcd/bbbbb/ccccc/ddddd");
        assertEquals("unordered", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testOrdered4_unordered_lengthsInRangeButNotOrdered_returnsUnorderedBody() {
        given().when().get(BASE + "/api/pat/ping4").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/ordered4/apple/apple/apple/apple");
        assertEquals("unordered", act.getBody().asString().trim());
    }
}