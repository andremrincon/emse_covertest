package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasing() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "dddddd", "ccccc");
        resp.then().assertThat().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturnsDecreasing() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        resp.then().assertThat().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testInRangeButUnorderedReturnsUnordered() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "zebra", "peach", "mango");
        resp.then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOutOfRangeLengthsReturnUnordered() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox_jumps_over_the_lazy_dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "a", "bbbbb", "ccccc", "ddddd");
        resp.then().assertThat().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testAllEqualStringsReturnUnordered() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "same1", "same1", "same1", "same1");
        resp.then().assertThat().body(equalTo("unordered"));
    }
}