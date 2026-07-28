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
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProducesIncreasing() {
        given().when().get("/api/pat/arrange-incr").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc").then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProducesDecreasing() {
        given().when().get("/api/pat/arrange-decr").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/dddddd/ccccc/aaaaa/bbbbb").then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testLengthsInvalidProducesUnordered() {
        given().when().get("/api/pat/arrange-short").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/shrt/bbbbb/ddddd/ccccc").then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsButNotOrderedProducesUnordered() {
        given().when().get("/api/pat/arrange-mixed").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/aaaaa/ccccc/ddddd/bbbbb").then().body(equalTo("unordered"));
    }
}