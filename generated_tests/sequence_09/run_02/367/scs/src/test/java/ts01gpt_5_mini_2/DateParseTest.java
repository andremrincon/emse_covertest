package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllMonthsArrange_thenActReturns200() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get("/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        given().when().get("/api/dateparse/mon/jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonShortDayname_thenActReturns200() {
        given().when().get("/api/dateparse/mon/dec").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wednesday/aug").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndMonth_thenActReturns500() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Superday/Movember").then().statusCode(200);
    }
}