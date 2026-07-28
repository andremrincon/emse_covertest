package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValueLongAndStartsWithUser() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String unique = "user" + UUID.randomUUID().toString().replace("-", "");
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", unique, "example.com");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZeroWhenValueTooShort() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "localhost");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneForAmAndAbcCom() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoForOtherValues() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "no", "other.com");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "random", "anything", "site.com");
        assertEquals("0", act.getBody().asString());
    }
}