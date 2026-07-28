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
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridPrefixMatchesReturnsOne() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortOrNoPrefixReturnsZero() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "us1", "localhost");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmWithAbcComReturnsOne() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherValuesReturnTwo() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "example.com");
        act.then().body(equalTo("2"));
    }
}