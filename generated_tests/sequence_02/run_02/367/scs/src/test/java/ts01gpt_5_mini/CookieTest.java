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
    public static void init() {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidUserPrefix() {
        given().when().get("/api/pat/{txt}", "health-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "UserID", "UserXYZ12", "Example.COM").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValueResultsZero() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "uSer1", "example.com").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridLongNonUserPrefixResultsZero() {
        given().when().get("/api/pat/{txt}", "arrange-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "USERID", "AdminUser123", "localhost").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmWithAbcComReturnsOne() {
        given().when().get("/api/pat/{txt}", "prep-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "AM", "abc.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        given().when().get("/api/pat/{txt}", "prep2-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "something", "example.com").then().assertThat().body(equalTo("2"));
    }
}