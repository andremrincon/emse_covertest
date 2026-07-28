package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {
    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValueReturnsOne() {
        given().when().get("/api/pat/hello").then().statusCode(lessThan(300));
        String val = "user" + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValueReturnsZero() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        String val = "user1";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "localhost");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmWithAbcComReturnsOne() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "bm", "example.com");
        resp.then().body(equalTo("2"));
    }
}