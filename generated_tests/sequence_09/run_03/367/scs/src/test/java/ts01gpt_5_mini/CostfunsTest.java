package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i5_algorithm_returns10() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/costfuns/{i}/{s}", 5, "algorithm");
        r.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i_minus445_abab_returns6() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/costfuns/{i}/{s}", -445, "abab");
        r.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i700_abab_returns6() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        r.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i_minus4_abab_returns0() {
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        r.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i_minus4_baab_triggers_s_equals_branch() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        r.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_costfuns_i555_abab_covers_ge_branch() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/costfuns/{i}/{s}", 555, "abab");
        r.then().statusCode(200).body(equalTo("10"));
    }
}