package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE_URL", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidStartsWithUserReturns1() {
        String unique = "user" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", unique, "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridTooShortReturns0() {
        String val = "user1";
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcDotComReturns1() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherValuesReturn2() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "other.com");
        act.then().body(equalTo("2"));
    }
}