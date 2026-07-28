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
    public static void init() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", System.getenv("api.base"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOne_whenValLongStartsWithUser() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZero_whenValLongDoesNotStartWithUser() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcdefghi", "example.com");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZero_whenValShort() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOne_whenValAmAndSiteAbcCom() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwo_whenValOrSiteDifferent() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "other.com");
        assertEquals("2", resp.asString());
    }
}