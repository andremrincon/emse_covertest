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
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserReturnsOne() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "User12345", "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturnsZero() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "USERID", "abc", "localhost");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmWithAbcComReturnsOne() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "a", "b", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "amx", "example.com");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", arrangeId).then().statusCode(400);
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "randomName", "someVal", "site.com");
        act.then().body(equalTo("0"));
    }
}