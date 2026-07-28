package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE");
        String prop = System.getProperty("api.base");
        String base = env != null ? env : (prop != null ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_nonMatchingName_returnsZero() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "foo", "bar", "example.com");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_userid_shortValue_returnsZero() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abc", "localhost");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_userid_longNonUserPrefix_returnsZero() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "zzzzzzz", "example.com");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_userid_longWithUserPrefix_returnsOne() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_session_am_and_abcdotcom_returnsOne() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_session_other_returnsTwo() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "other.com");
        assertEquals("2", resp.getBody().asString());
    }
}