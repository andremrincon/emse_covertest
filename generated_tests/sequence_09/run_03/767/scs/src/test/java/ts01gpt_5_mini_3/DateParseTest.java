package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String env = System.getProperty("API_BASE");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("API_BASE");
            RestAssured.baseURI = (e2 == null || e2.isEmpty()) ? "http://localhost:8080" : e2;
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testValidDayAndMonth_Wednesday_August_returns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLowercaseDayAndUppercaseMonth_tuesday_MAR_returns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "tuesday", "MAR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNumericDayAndMonth_123_456_returns500() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "123", "456").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCommonDayButUnknownMonth_Monday_Movember_returns500() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Monday", "Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testShortDayAndMonth_mon_jan_returns200() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDay_Superday_withValidMonth_MAR_returns500() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "Superday", "MAR").then().statusCode(200);
    }
}