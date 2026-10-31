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
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidStartsWithUser() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String unique = "user" + UUID.randomUUID().toString().replace("-", "").substring(0,6);
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", unique + "xyz", "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridInvalidTooShort() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user", "localhost");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionExactMatchAmAbcCom() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingGivesTwo() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "amx", "example.com");
        act.then().body(equalTo("2"));
    }
}