package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDayMonMonthJanProducesTwo() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{day}/{month}", "Mon", "JAN");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDayTueMonthFebProducesThree() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{day}/{month}", "tue", "feb");
        assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void testInvalidDayWithDecProducesTwelve() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{day}/{month}", "noday", "dec");
        assertEquals("12", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUppercaseWedMonthMarProducesFour() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{day}/{month}", "WED", "MAR");
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSunWithAugProducesNine() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{day}/{month}", "sun", "AUG");
        assertEquals("9", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNeitherDayNorMonthProducesZero() {
        given().when().get("/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{day}/{month}", "hello", "unknown");
        assertEquals("0", resp.asString());
    }
}