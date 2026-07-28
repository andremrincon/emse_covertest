package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {
    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValLongAndStartsWithUser() {
        String val = "user" + UUID.randomUUID().toString().replace("-", "");
        String site = "example.com";
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, site).then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZeroWhenValTooShortOrNoUserPrefix() {
        String val = "usr" + UUID.randomUUID().toString().replace("-", "").substring(0,2);
        String site = "localhost";
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, site).then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneForAmAndAbcDotCom() {
        String name = "session";
        String val = "am";
        String site = "abc.com";
        given().when().get("/api/pat/{txt}", "quick").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site).then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoForOtherValues() {
        String name = "session";
        String val = "pm";
        String site = "example.com";
        given().when().get("/api/pat/{txt}", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site).then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        String name = "randomName";
        String val = UUID.randomUUID().toString();
        String site = "site.example";
        given().when().get("/api/pat/{txt}", "fox").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", name, val, site).then().body(equalTo("0"));
    }
}