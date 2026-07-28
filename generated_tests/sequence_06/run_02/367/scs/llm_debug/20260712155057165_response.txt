package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/userid/userabcd1/example.com").then().extract().response();
        String body = act.asString();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingWithUserReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/userid/foobarx/example.com").then().extract().response();
        String body = act.asString();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/userid/usr/ex.example").then().extract().response();
        String body = act.asString();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testSessionAmAbcComReturnsOneCaseInsensitive() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/SESSION/AM/ABC.COM").then().extract().response();
        String body = act.asString();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturnsTwo() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/session/am/other.com").then().extract().response();
        String body = act.asString();
        Assert.assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/unknown/value/example.com").then().extract().response();
        String body = act.asString();
        Assert.assertEquals("0", body);
    }
}