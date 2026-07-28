package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "banan", "delta", "candy");
        r.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given().when().get("/api/pat/{txt}", "health check").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx");
        r.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLength() {
        given().when().get("/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "apple", "delta", "candy");
        r.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToOrderMismatch() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "candy", "delta", "banan");
        r.then().body(equalTo("unordered"));
    }
}