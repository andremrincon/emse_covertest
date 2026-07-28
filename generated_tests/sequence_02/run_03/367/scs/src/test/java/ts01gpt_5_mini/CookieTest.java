package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOne() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "noam", "example.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcduser9", "example.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "userabcd1", "example.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortReturnsZero() {
        given().when().get("/api/pat/setup").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "userabcd1", "example.org").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.org").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionInvalidReturnsTwo() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcd1234", "site.local").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "notam", "abc.com").then().body(equalTo("2"));
    }
}