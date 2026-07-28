package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {
    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void userid_valStartsWithUser_returns1() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        String name = "USERID";
        String val = "UserExtraValue" + UUID.randomUUID().toString();
        String site = "Example.COM";
        Response res = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", name, val, site);
        Assert.assertEquals("1", res.asString());
    }

    @Test(timeout = 60000)
    public void userid_valLongButNotStartingWithUser_returns0() {
        given().when().get(BASE + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "longvalue-notuser-" + UUID.randomUUID().toString();
        String site = "localhost";
        Response res = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", name, val, site);
        Assert.assertEquals("0", res.asString());
    }

    @Test(timeout = 60000)
    public void userid_valShortEvenIfStartsWithUser_returns0() {
        given().when().get(BASE + "/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        String name = "UserID";
        String val = "User1";
        String site = "example.com";
        Response res = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", name, val, site);
        Assert.assertEquals("0", res.asString());
    }

    @Test(timeout = 60000)
    public void session_withAmAndAbcDotCom_returns1() {
        given().when().get(BASE + "/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        String name = "session";
        String val = "AM";
        String site = "ABC.COM";
        Response res = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", name, val, site);
        Assert.assertEquals("1", res.asString());
    }

    @Test(timeout = 60000)
    public void session_withOtherValues_returns2() {
        given().when().get(BASE + "/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        String name = "session";
        String val = "notam";
        String site = "other.com";
        Response res = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", name, val, site);
        Assert.assertEquals("2", res.asString());
    }

    @Test(timeout = 60000)
    public void otherName_returns0() {
        given().when().get(BASE + "/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        String name = "somethingElse";
        String val = "whatever";
        String site = "abc.com";
        Response res = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", name, val, site);
        Assert.assertEquals("0", res.asString());
    }
}