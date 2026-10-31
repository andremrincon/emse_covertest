package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_costfuns_known200_example_i1_returns_200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_costfuns_i_equals_5_returns_non_empty_body() {
        given().when().get("/api/pat/{txt}", "The%20quick").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "a").then().body(not(isEmptyString()));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i_less_than_negative_444_returns_non_empty_body() {
        given().when().get("/api/pat/{txt}", "abc").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "test").then().body(not(isEmptyString()));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i_less_or_equal_negative_333_returns_non_empty_body() {
        given().when().get("/api/pat/{txt}", "xyz").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -333, "test").then().body(not(isEmptyString()));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i_greater_than_666_or_ge_555_returns_non_empty_body() {
        given().when().get("/api/pat/{txt}", "sample").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 700, "some").then().body(not(isEmptyString()));
    }

    @Test(timeout = 60000)
    public void test_costfuns_string_equality_and_comparison_branches_return_non_empty_body() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "baab").then().body(not(isEmptyString()));
    }
}