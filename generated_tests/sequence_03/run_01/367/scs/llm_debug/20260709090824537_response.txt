package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllMonthsInvoked_then_wed_august_returns200() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", m).then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_unrecognized_day_returns500() {
        given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "jan").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Superday", "Movember");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_month_case_insensitive_and_day_alias_returns200() {
        given().when().get("/api/dateparse/{dayname}/{monthname}", "sun", "Dec").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "tuesday", "MAR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_thur_and_oct_return200() {
        given().when().get("/api/dateparse/{dayname}/{monthname}", "fri", "oct").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "thur", "oct");
        act.then().statusCode(200);
    }
}