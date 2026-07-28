package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("test.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidDayWedMonthMar_returnsBody4() {
        given().when().get("/api/pat/{txt}", "healthcheck1").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "wed", "mar").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testInvalidDayHello_validMonthAug_returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "hello", "aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDayThurMonthJan_returnsBody2() {
        given().when().get("/api/pat/{txt}", "healthcheck3").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "thur", "jan").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testDaySunMonthDec_returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck4").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "sun", "dec").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCaseInsensitiveMON_May_returnsBody6() {
        given().when().get("/api/pat/{txt}", "healthcheck5").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "MON", "May").then().body(equalTo("6"));
    }

    @Test(timeout = 60000)
    public void testNumericDayAndUnknownMonth_returns500() {
        given().when().get("/api/pat/{txt}", "healthcheck6").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{dayname}/{monthname}", "123", "Movember").then().statusCode(200);
    }
}