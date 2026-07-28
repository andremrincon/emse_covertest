package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String configured = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = configured;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValueStartsWithUserAndLongEnough() {
        given().when().get("/api/pat/heartbeat").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneForAmAndAbcCom() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "random", "something", "example.com").then().body(equalTo("0"));
    }
}