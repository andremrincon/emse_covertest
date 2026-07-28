package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE_URL", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasing() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc");
        resp.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturnsDecreasing() {
        given().when().get("/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        resp.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testLengthViolationReturnsUnordered() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcd", "bbbbb", "ddddd", "ccccc");
        resp.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsButNonMonotonicReturnsUnordered() {
        given().when().get("/api/notypevar/5/example-string").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "ddddd", "bbbbb");
        resp.then().body(equalTo("unordered"));
    }
}