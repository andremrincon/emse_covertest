package ts01gpt_5_mini;

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
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidReturnsOne() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "other", unique, "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionInvalidReturnsTwo() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1", "example.com").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "other", unique, "localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "notam", "abc.com");
        assertEquals("2", act.getBody().asString());
    }
}