package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
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
        given().when().get("/api/pat/{txt}", "arrange-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "Mon", "Jan");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoDayWithMonthDecProducesTwelve() {
        given().when().get("/api/pat/{txt}", "arrange-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "noday", "Dec");
        assertEquals("12", resp.asString());
    }

    @Test(timeout = 60000)
    public void testMixedCaseDayAndMonthAugProducesNine() {
        given().when().get("/api/pat/{txt}", "arrange-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "tUE", "AuG");
        assertEquals("9", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNonMatchingDayWithMonthMarProducesThree() {
        given().when().get("/api/pat/{txt}", "arrange-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "xyz", "mar");
        assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDayFriWithMonthMayProducesSix() {
        given().when().get("/api/pat/{txt}", "arrange-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "FRI", "may");
        assertEquals("6", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDayLowercaseWithMonthFebProducesTwo() {
        given().when().get("/api/pat/{txt}", "arrange-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/dateparse/{dayname}/{monthname}", "sun", "FEB");
        assertEquals("3", resp.asString());
    }
}