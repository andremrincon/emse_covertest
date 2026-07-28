package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {
    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixReturnsOne() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        String unique = "user" + UUID.randomUUID().toString().replace("-", "").substring(0, 6) + "x";
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", unique, "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongNonUserPrefixReturnsZero() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        String val = "abcd1234ef";
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValueReturnsZero() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        String val = "user1";
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcDotComReturnsOne() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithOtherValuesReturnsTwo() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "notam", "example.com");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "other", "someval", "somewhere.com");
        act.then().body(equalTo("0"));
    }
}