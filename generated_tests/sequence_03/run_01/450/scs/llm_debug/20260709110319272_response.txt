package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_sum_equals_56_triggers_internal_branch_and_returns_result() {
        RestAssured.given().when().get("/api/costfuns/0/algorithm").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/notypevar/{i}/{s}", 28, "zzz");
        String body = resp.asString();
        if (body != null && body.length() >= 2 && body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void test_hello_plus_y_equals_hello7_branch_executed() {
        RestAssured.given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/notypevar/{i}/{s}", 7, "alpha");
        String body = resp.asString();
        if (body != null && body.length() >= 2 && body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void test_compareTo_less_than_sets_result_two_and_returns_two() {
        RestAssured.given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/notypevar/{i}/{s}", 5, "world");
        String body = resp.asString();
        if (body != null && body.length() >= 2 && body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void test_y_greater_than_five_sets_result_three() {
        RestAssured.given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zebra", "yak", "x-ray", "wolf").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/notypevar/{i}/{s}", 6, "a");
        String body = resp.asString();
        if (body != null && body.length() >= 2 && body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void test_no_branch_taken_returns_zero() {
        RestAssured.given().when().get("/api/filesuffix/{directory}/{file}", "/home/user/documents", "quarterly_report.docx").then().statusCode(400);
        Response resp = RestAssured.given().when().get("/api/notypevar/{i}/{s}", 5, "a");
        String body = resp.asString();
        if (body != null && body.length() >= 2 && body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("0", body);
    }
}