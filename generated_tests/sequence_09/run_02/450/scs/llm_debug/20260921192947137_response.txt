package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValLongAndStartsWithUser() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "USERID", "UserABC123", "anysite.com");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZeroWhenValTooShortOrNotStartingWithUser() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneWhenValAmAndSiteAbcCom() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "SESSION", "AM", "ABC.COM");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoForOtherValues() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "notam", "example.com");
        assertEquals("2", act.getBody().asString());
    }
}