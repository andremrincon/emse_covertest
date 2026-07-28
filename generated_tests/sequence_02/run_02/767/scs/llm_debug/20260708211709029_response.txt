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
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("api.base", System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUserIdHappyPath() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "longbutnotuser", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "userABC7", "example.com");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturnsTwo() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "userABC7", "example.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "no", "abc.com");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnknownNameYieldsZero() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "unknown", "anything", "some.site");
        assertEquals("0", resp.asString());
    }
}