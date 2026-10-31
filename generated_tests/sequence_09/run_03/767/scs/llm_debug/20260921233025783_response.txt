package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com")
               .then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridNotStartingWithUserReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr12345", "EXAMPLE.COM")
               .then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com")
               .then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionAmWithDifferentSiteReturnsTwo() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "example.com")
               .then().body(equalTo("2"));
    }
}