package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
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
    public void test_subject_with_i28_triggers_sum_branch_and_final_overwrite() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        assertEquals("3", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_subject_with_i7_triggers_hello_concat_branch_and_overwrite() {
        given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals("3", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_subject_string_compare_branch_sets_result_two() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 1, "z");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_subject_y_greater_than_x_sets_result_three() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 6, "a");
        assertEquals("3", act.getBody().asString());
    }
}