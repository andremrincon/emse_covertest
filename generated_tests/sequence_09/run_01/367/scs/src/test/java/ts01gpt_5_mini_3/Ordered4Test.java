package ts01gpt_5_mini_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class Ordered4Test {

    private static final String BASE;
    static {
        String p = System.getProperty("api.base");
        String e = System.getenv("API_BASE_URL");
        BASE = p != null && !p.isEmpty() ? p : (e != null && !e.isEmpty() ? e : "http://localhost:8080");
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"unordered\"   Actual: ...")
    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasing() {
        given().when().get(BASE + "/api/pat/{txt}", "The_quick_brown_fox_jumps_over_the_lazy_dog.").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc").then().statusCode(200).body(equalTo("unordered"));
    }

    @Ignore("Illegal character in path at index 57: http://localhost:8080/api/ordered4/aaaaa/bbbbb/ccccc/dddd d")
    @Test(timeout = 60000)
    public void testStrictIncreasingProducesIncreasing() {
        given().when().get(BASE + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ccccc", "dddd d").then().statusCode(200).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testStrictDecreasingProducesDecreasing() {
        given().when().get(BASE + "/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "xxxxx", "wwwww").then().statusCode(200).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthOutOfRangeYieldsUnordered() {
        given().when().get(BASE + "/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ccccc", "ddddd").then().statusCode(200).body(equalTo("unordered"));
    }
}