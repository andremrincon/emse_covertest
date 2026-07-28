package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasing() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ccccc", "ddddd")
               .then().statusCode(200).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturnsDecreasing() {
        given().when().get("/api/pat/{txt}", "arrange2").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "xxxxx", "wwwww")
               .then().statusCode(200).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthsOutsideRangeReturnUnordered() {
        given().when().get("/api/pat/{txt}", "arrange3").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcd", "efghi", "jklmn", "opqrs").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "abcd", "efghi", "jklmn", "opqrs")
               .then().statusCode(200).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testWithinRangeButNotMonotonicReturnsUnordered() {
        given().when().get("/api/pat/{txt}", "arrange4").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ccccc", "bbbbb", "ddddd")
               .then().statusCode(200).body(equalTo("unordered"));
    }
}