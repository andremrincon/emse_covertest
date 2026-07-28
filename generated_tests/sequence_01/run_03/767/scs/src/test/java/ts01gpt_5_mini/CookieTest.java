package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", System.getenv("BASE_URL"));
    }

    @Test(timeout = 60000)
    public void testUseridValid_returns1() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "userabcd", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShort_returns0() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionMatch_returns1() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionMismatch_returns2() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "other.com");
        resp.then().body(equalTo("2"));
    }
}