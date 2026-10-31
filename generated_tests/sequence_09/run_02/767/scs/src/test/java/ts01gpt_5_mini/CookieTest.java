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
        String base = System.getProperty("api.base");
        if (base == null) {
            base = System.getenv().getOrDefault("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void useridWithLongUserPrefixReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        assertEquals("1", res.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void useridWithSixCharsPrefixReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12", "localhost");
        assertEquals("0", res.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void sessionAmWithAbcComReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", res.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void sessionOtherReturnsTwo() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "bm", "example.com");
        assertEquals("2", res.getBody().asString().trim());
    }
}