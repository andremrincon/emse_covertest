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
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasingBody() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturnsDecreasingBody() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String w = "zzzzz";
        String x = "yyyyy";
        String z = "wwwww";
        String y = "xxxxx";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ReturnsUnorderedWhenLengthsSatisfyButOrderDoesNot() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String w = "apple";
        String x = "zzzzz";
        String z = "qqqqq";
        String y = "mmmmm";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ReturnsUnorderedWhenLengthsOutOfRange() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String w = "a";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";
        Response resp = given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);
        resp.then().body(equalTo("unordered"));
    }
}