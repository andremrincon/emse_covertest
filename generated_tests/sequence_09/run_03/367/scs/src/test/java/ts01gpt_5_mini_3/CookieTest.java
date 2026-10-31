package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_SERVER_BASE", System.getenv("TEST_SERVER_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", System.getenv("BASE_URL"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridWithUserPrefixReturnsOne() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "UserId", "Userabcd", "example.com");
        String body = resp.getBody().asString().trim();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testUseridWithoutUserPrefixReturnsZero() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "nope1234", "example.com");
        String body = resp.getBody().asString().trim();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcDotComReturnsOne() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "SESSION", "am", "abc.com");
        String body = resp.getBody().asString().trim();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testSessionWithDifferentSiteOrValReturnsTwo() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "example.com");
        String body = resp.getBody().asString().trim();
        Assert.assertEquals("2", body);
    }
}