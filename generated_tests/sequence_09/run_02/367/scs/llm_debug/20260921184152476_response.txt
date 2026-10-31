package ts01gpt_5_mini_2;

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
    public void testUseridWithUserPrefixReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String unique = "user" + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", unique, "example.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotUserPrefixReturnsZero() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        String val = "abcdefg";
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "localhost");
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComReturnsOne() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        given().when().get("/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "xm", "example.com");
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "other", "somevalue", "example.com");
        act.then().assertThat().body(equalTo("0"));
    }
}