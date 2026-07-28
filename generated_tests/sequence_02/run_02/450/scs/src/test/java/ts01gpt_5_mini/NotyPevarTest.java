package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns_2_for_i3_with_s_greater_than_hello() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 3, "world");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_executes_hello7_branch_and_returns_3_for_i7() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "unused");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_executes_double_28_branch_and_returns_3_for_i28() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "z");
        resp.then().body(equalTo("3"));
    }
}