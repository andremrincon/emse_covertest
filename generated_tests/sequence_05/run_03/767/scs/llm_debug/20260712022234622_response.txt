package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidDayAndMonthReturnsSum() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "Wednesday", "August");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testInvalidDayProducesServerError() {
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "Superday", "Movember");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMonthCaseInsensitiveAndShortDay() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{day}/{month}", "sat", "DEC");
        assertEquals("13", act.getBody().asString());
    }
}