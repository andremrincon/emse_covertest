package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void test_increasing_returns_increasing() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        String w = "aaaaa";
        String x = "aaaab";
        String y = "aaaac";
        String z = "aaaad";
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("increasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_decreasing_returns_decreasing() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        String w = "zzzzz";
        String x = "yyyyy";
        String y = "xxxxx";
        String z = "ttttt";
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("decreasing", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_length_mismatch_returns_unordered() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        String w = "four";
        String x = "aaaaa";
        String y = "bbbbb";
        String z = "ccccc";
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_within_length_but_not_ordered_returns_unordered() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        String w = "aaaaa";
        String x = "ccccc";
        String y = "bbbbb";
        String z = "ddddd";
        Response act = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        assertEquals("unordered", act.getBody().asString());
    }
}