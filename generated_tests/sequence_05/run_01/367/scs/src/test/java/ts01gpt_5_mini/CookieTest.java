package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUserIdValidReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdInvalidReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12", "example.com");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionMatchReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchReturnsTwo() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "bm", "abc.com");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "random", "anything", "example.com");
        resp.then().body(equalTo("0"));
    }
}