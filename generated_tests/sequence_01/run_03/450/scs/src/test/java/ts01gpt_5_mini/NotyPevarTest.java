package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {
    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null) b = System.getenv("API_BASE_URL");
        if (b == null) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void test_notypevar_executes_branches_i0_i2_i3_status200() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/{i}/{s}", 28, "zzzz");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_notypevar_executes_branch_i1_then_i3_bodyEquals3() {
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/{i}/{s}", 7, "a");
        assertEquals("3", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_notypevar_when_y_le_5_executes_i2_bodyEquals2() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/{i}/{s}", 3, "zzzz");
        assertEquals("2", act.getBody().asString());
    }
}