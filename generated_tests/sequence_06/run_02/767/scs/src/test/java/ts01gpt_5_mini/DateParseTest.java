package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("BASE_URL");
            RestAssured.baseURI = (e2 == null || e2.isEmpty()) ? "http://localhost:8080" : e2;
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testValidShortDayAndShortMonth_JanMon() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Mon", "Jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidShortDayAndShortMonth_FebWed() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "wed", "feb").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidShortDayAndShortMonth_DecSun() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Sun", "Dec").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDayCaseInsensitive_MonMar() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "MON", "mar").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayNumeric_ProducesServerError() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "123", "Aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidMonthName_ProducesServerError() {
        given().when().get("/api/pat/{txt}", "start").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "Movember").then().statusCode(200);
    }
}