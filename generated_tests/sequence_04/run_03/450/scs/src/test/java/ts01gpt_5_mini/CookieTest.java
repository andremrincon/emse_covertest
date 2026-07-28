package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String envBase = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        String base = System.getProperty("baseUrl", envBase);
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "unknown", "anything", "example.com").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValueReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "UserID", "usr", "localHost").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValueStartingUserReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "User12345", "example.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcDotComReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithOtherValuesReturnsTwo() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "other.com").then().assertThat().body(equalTo("2"));
    }
}