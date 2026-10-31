package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String cfg = System.getProperty("api.base");
        if (cfg == null) cfg = System.getenv("API_BASE");
        if (cfg == null) cfg = "http://localhost:8080";
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void test_i_greater_than_5_returns_3() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 6, "any").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_executes_xplusy_equals_56_branch_with_i_28() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "aaa").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_executes_hello7_branch_with_i_7() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "zzz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_compareTo_branch_sets_2_when_s_greater_than_hello() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 5, "zeta").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_default_result_zero_when_no_conditions_met() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 5, "aaa").then().body(equalTo("0"));
    }
}