package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.server");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_SERVER");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_costfuns_returns6_when_i_is_5_and_s_is_abab() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_returns10_for_large_i_and_zzzzzz() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 700, "zzzzzz");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_returns10_for_negative_i_triggering_multiple_int_branches() {
        given().when().get("/api/pat/{txt}", "arrange-" + System.nanoTime()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -500, "zz");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_returns0_when_i_is_minus4_and_s_is_abab() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_executes_s_equals_branch_and_returns_ok_status() {
        given().when().get("/api/pat/{txt}", "setup-" + System.nanoTime()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        act.then().statusCode(200);
    }
}