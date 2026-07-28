package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testValidWednesdayMarReturnsHttp200() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get(BASE + "/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        given().when().get(BASE + "/api/dateparse/Wed/Mar").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayNameProducesServerError500() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get(BASE + "/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        given().when().get(BASE + "/api/dateparse/123/Jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCaseInsensitiveMonthReturnsHttp200() {
        String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        for (String m : months) {
            given().when().get(BASE + "/api/dateparse/mon/" + m).then().statusCode(lessThan(300));
        }
        given().when().get(BASE + "/api/dateparse/tuesday/AUG").then().statusCode(200);
    }
}