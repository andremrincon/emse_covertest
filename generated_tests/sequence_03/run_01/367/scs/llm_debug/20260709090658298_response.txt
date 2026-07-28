package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {
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
    public void userid_with_short_value_returns_0() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr1", "example.com");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void userid_with_long_value_starting_user_returns_1() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "userabc", "example.com");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void userid_with_long_value_not_starting_user_returns_0() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcd1234", "example.com");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void session_with_am_and_abc_com_returns_1() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void session_with_other_values_returns_2() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "othersite.com");
        assertEquals("2", act.asString());
    }
}