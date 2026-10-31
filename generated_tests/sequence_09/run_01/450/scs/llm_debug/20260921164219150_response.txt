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
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUserIdValid() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/userid/user1234/example.com");
        assertEquals("1", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUserIdInvalidPrefix() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/userid/xyzuser/example.com");
        assertEquals("0", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionMatched() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/session/am/abc.com");
        assertEquals("1", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionMismatched() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/session/am/example.com");
        assertEquals("2", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testOtherNameDefaultsToZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/other/someval/some.site");
        assertEquals("0", r.getBody().asString());
    }
}