package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    static {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue_returns0() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1", "example.com");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValueWrongPrefix_returns0() {
        given().when().get("/api/pat/{txt}", "quick").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcd12345", "localhost");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValueCorrectPrefix_returns1() {
        given().when().get("/api/pat/{txt}", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcDotCom_returns1() {
        given().when().get("/api/pat/{txt}", "fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionWithNonMatchingSiteOrVal_returns2() {
        given().when().get("/api/pat/{txt}", "jumps").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "example.com");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testOtherNameReturns0() {
        given().when().get("/api/pat/{txt}", "lazy").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "other", "someval", "site.com");
        assertEquals("0", resp.getBody().asString());
    }
}