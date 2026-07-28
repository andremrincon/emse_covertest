package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        RestAssured.baseURI = (base == null || base.isEmpty()) ? "http://localhost:8080" : base;
    }

    @Test(timeout = 60000)
    public void userid_returns_one_when_value_long_and_prefix_user() {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void userid_returns_zero_when_value_too_short() {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "u1", "example.com");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void userid_returns_zero_when_prefix_not_user_even_if_long() {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcd12345", "localhost");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void session_returns_one_for_am_and_abc_com() {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void session_returns_two_for_other_values() {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com");
        resp.then().body(equalTo("2"));
    }
}