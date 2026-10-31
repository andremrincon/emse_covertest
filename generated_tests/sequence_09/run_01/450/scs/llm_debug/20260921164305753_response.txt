package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("test.baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMonJanReturns2() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", unique, "localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "mon", "jan");
        Assert.assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testTueFebReturns3() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", unique, "localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "tue", "feb");
        Assert.assertEquals("3", act.asString());
    }

    @Test(timeout = 60000)
    public void testWedDecReturns13() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", unique, "localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "wed", "dec");
        Assert.assertEquals("13", act.asString());
    }

    @Test(timeout = 60000)
    public void testUnknownDayMarReturns3() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", unique, "localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "unknown", "mar");
        Assert.assertEquals("3", act.asString());
    }

    @Test(timeout = 60000)
    public void testThurOctReturns11() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", unique, "localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "thur", "oct");
        Assert.assertEquals("11", act.asString());
    }

    @Test(timeout = 60000)
    public void testSunUnknownMonthReturns1() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", unique, "localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "sun", "unknownmonth");
        Assert.assertEquals("1", act.asString());
    }
}