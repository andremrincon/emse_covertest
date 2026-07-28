package ts01gpt_5_mini;

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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void increasingOrderReturnsIncreasing() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "aaaab", "aaaad", "aaaac");
        resp.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void decreasingOrderReturnsDecreasing() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb");
        resp.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void lengthTooShortReturnsUnordered() {
        given().when().get("/api/pat/{txt}", "prepare").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "aaaaa", "bbbbb", "ccccc");
        resp.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void equalStringsWithinLengthReturnUnordered() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "samee", "samee", "samee", "samee");
        resp.then().body(equalTo("unordered"));
    }
}