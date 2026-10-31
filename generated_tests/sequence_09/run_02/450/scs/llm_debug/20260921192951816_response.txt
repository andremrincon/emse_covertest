package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotypevar_returnsZero_for_i4_and_s_aaa() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 4, "aaa");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_returnsTwo_when_compareTo_s_greater_world_and_i3() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 3, "world");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_hello7_branch_executes_but_final_is_three_for_i7() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "zzz");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_xplusy_equals_56_branch_executes_for_i28_and_final_is_three() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 28, "aaa");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_invalid_integer_returns400_for_abc() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", "abc", "some-string");
        act.then().statusCode(400);
    }
}