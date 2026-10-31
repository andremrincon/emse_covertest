package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserReturnsOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "userXYZ1";
        String site = "example.com";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingUserReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "abcd1234";
        String site = "localhost";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "usr1";
        String site = "example.com";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComReturnsOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String name = "session";
        String val = "am";
        String site = "abc.com";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String name = "session";
        String val = UUID.randomUUID().toString().substring(0,4);
        String site = "other.com";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String name = "unknown";
        String val = "some-value";
        String site = "example.com";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("0", resp.getBody().asString());
    }
}