package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_i5_s_baab_returns_10() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/5/baab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_i_negative_thousand_s_ababba_executes_compare_ge_branch() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-1000/ababba").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_i_minus4_checks_not_equal_branch_false() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-4/abab").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_zero_with_high_string_triggers_compareTo_greater() {
        given().when().get("/api/ordered4/zebra/yak/x-ray/wolf").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/0/zzzz").then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_standard_example_returns_status_200() {
        given().when().get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_boundary_minus333_triggers_leq_branch() {
        given().when().get("/api/cookie/session-id/1/localhost").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/-333/abab").then().body(equalTo("10"));
    }
}