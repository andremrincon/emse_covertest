package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.server");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_SERVER");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testOrdered4ReturnsIncreasingWhenStrictlyIncreasingStringsOfLength5To6() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "beret", "delta", "candy").then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ReturnsDecreasingWhenStrictlyDecreasingStringsOfLength5To6() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "delta", "carol", "alpha", "bravo").then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ReturnsUnorderedWhenLengthsOutOfRange() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "a", "b", "c", "d").then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ReturnsUnorderedWhenLengthsInRangeButNotOrdered() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "apple", "apple").then().body(equalTo("unordered"));
    }
}