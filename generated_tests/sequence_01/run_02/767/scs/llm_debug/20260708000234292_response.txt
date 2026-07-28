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
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidStartsWithUserProducesOne() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "UserId", "User12345", "example.com");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridShortValueProducesZero() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "us1", "example.com");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingWithUserProducesZero() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "USERID", "randomval", "localhost");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComProducesOne() {
        given().when().get("/api/pat/{txt}", "prepare").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingSiteProducesTwo() {
        given().when().get("/api/pat/{txt}", "prime").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "other.com");
        assertEquals("2", act.getBody().asString());
    }
}