package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("api.base.url");
        if (url == null || url.isEmpty()) url = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserAndLong() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingWithUser() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcd12345", "example.com").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturnsZero() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1", "example.com").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcDotComReturnsOne() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithOtherValueReturnsTwo() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "unknown", "anything", "example.com").then().body(equalTo("0"));
    }
}