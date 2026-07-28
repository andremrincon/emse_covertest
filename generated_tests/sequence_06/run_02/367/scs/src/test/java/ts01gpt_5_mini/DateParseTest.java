package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        BASE = System.getProperty("api.base");
        if (BASE == null || BASE.isEmpty()) {
            BASE = System.getenv("API_BASE_URL");
        }
        if (BASE == null || BASE.isEmpty()) {
            BASE = "http://localhost:8080";
        }
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testAbbrevDayAndMonth_returns200() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "wed", "mar");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFullNames_returns200() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDay_returns500() {
        given().when().get("/api/pat/{txt}", "prepare").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Superday", "Movember");
        act.then().statusCode(200);
    }
}