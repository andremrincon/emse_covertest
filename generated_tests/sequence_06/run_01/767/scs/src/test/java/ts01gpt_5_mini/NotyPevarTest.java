package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        base = prop != null && !prop.isEmpty() ? prop : (env != null && !env.isEmpty() ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void when_i_greater_than_5_then_response_is_3() {
        given().when().get(base + "/api/pat/{txt}", "abc").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/notypevar/{i}/{s}", 6, "any");
        assertEquals("3", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void when_i_equal_5_and_s_greater_than_hello_then_response_is_2() {
        given().when().get(base + "/api/pat/{txt}", "abc").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/notypevar/{i}/{s}", 5, "world");
        assertEquals("2", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void when_i_less_or_equal_5_and_s_not_greater_then_response_is_0() {
        given().when().get(base + "/api/pat/{txt}", "abc").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/notypevar/{i}/{s}", 0, "aaa");
        assertEquals("0", act.getBody().asString().trim());
    }
}