package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("test.server");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_SERVER");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonMatchingNameReturnsZero() {
        RestAssured.given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String body = RestAssured.given().when().get("/api/cookie/session-id/abc-123/example.com").then().extract().asString();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValueReturnsZero() {
        RestAssured.given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String body = RestAssured.given().when().get("/api/cookie/userid/usr/localhost").then().extract().asString();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testUseridWithLongNonUserPrefixReturnsZero() {
        RestAssured.given().when().get("/api/pat/banana").then().statusCode(lessThan(300));
        String val = "abcdefg" + UUID.randomUUID().toString();
        String path = "/api/cookie/userid/" + val + "/example.com";
        String body = RestAssured.given().when().get(path).then().extract().asString();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testUseridWithUserPrefixAndLongValueReturnsOne() {
        RestAssured.given().when().get("/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        String val = "user" + UUID.randomUUID().toString();
        String path = "/api/cookie/userid/" + val + "/example.com";
        String body = RestAssured.given().when().get(path).then().extract().asString();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcComReturnsOne() {
        RestAssured.given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        String body = RestAssured.given().when().get("/api/cookie/session/am/abc.com").then().extract().asString();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testSessionWithOtherValuesReturnsTwo() {
        RestAssured.given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        String val = "bm" + UUID.randomUUID().toString();
        String body = RestAssured.given().when().get("/api/cookie/session/" + val + "/localhost").then().extract().asString();
        Assert.assertEquals("2", body);
    }
}