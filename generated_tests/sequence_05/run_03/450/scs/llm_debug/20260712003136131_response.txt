package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserReturnsOne() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testUseridShortReturnsZero() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr1", "localhost");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotUserPrefixReturnsZero() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "admin12345", "example.com");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testSessionAmWithAbcComReturnsOne() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        given().when().get("/api/pat/status").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "other.com");
        assertEquals("2", act.asString());
    }
}