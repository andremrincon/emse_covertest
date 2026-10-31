package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("TEST_BASE_URL");
        String prop = System.getProperty("test.server");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else if (prop != null && !prop.isEmpty()) {
            RestAssured.baseURI = prop;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testReturnsIncreasingWhenStringsStrictlyIncreasingAndLengths5to6() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "dddddd", "ccccc");
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testReturnsDecreasingWhenStringsStrictlyDecreasingAndLengths5to6() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testReturnsUnorderedWhenAParameterLengthOutsideRange() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaa", "bbbbb", "ccccc", "ddddd");
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testReturnsUnorderedWhenLengthsOkButNoMonotonicOrder() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd");
        act.then().body(equalTo("unordered"));
    }
}