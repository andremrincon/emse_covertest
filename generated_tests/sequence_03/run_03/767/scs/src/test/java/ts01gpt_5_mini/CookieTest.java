package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
    public void testUseridStartsWithUserAndLongReturnsOne() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingWithUserReturnsZero() {
        given().when().get("/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcd12345", "localhost");
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturnsZero() {
        given().when().get("/api/pat/{txt}", "hc3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr1", "example.com");
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcDotComReturnsOne() {
        given().when().get("/api/pat/{txt}", "hc4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonAmReturnsTwo() {
        given().when().get("/api/pat/{txt}", "hc5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com");
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        given().when().get("/api/pat/{txt}", "hc6").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "random", "somevalue", "example.com");
        act.then().assertThat().body(equalTo("0"));
    }
}