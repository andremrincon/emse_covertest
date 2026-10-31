package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValueLongAndStartsWithUser() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/userid/user1234/example.com");
        Assert.assertEquals("1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZeroWhenValueShortOrNotStartingWithUser() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/userid/user1/example.com");
        Assert.assertEquals("0", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneWhenAmAndAbcCom() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/am/abc.com");
        Assert.assertEquals("1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoWhenNotMatchingAmAndAbcCom() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/xm/abc.com");
        Assert.assertEquals("2", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/randomname/somevalue/site.com");
        Assert.assertEquals("0", resp.getBody().asString().trim());
    }
}