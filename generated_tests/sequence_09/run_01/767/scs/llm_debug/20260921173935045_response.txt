package ts01gpt_5_mini_2;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {
    private static final String BASE = System.getProperty("api.base", System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");

    @Test(timeout = 60000)
    public void testIEqualsMinus4_ShouldReturn200() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/costfuns/{i}/{s}", -4, "abab");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEquals5AndSEqualsBaab_ShouldReturn200() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/costfuns/{i}/{s}", 5, "baab");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testILessThanMinus444_ShouldReturn200() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/costfuns/{i}/{s}", -500, "x");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666_SEqualAbabba_ShouldReturn200() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/costfuns/{i}/{s}", 667, "ababba");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIEquals555_SGreaterThanAbabba_ShouldReturn200() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/costfuns/{i}/{s}", 555, "zzzz");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExampleAlgorithm_ShouldReturn200() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/costfuns/{i}/{s}", 1, "algorithm");
        act.then().statusCode(200);
    }
}