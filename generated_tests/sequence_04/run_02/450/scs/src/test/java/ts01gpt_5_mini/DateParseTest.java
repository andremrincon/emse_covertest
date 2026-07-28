package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_BASE_URI");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_BASE_URI");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDateparse_fullNames_returns200() {
        String setupToken = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_shortNames_caseInsensitive_returnsCorrectValue() {
        String setupToken = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "wed", "AUG");
        act.then().body(equalTo("9"));
    }

    @Test(timeout = 60000)
    public void testDateparse_shortNames_mar_returnsCorrectValue() {
        String setupToken = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "tue", "MAR");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testDateparse_month_jan_with_valid_day_returnsCorrectValue() {
        String setupToken = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "jan");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testDateparse_invalidDay_returns500() {
        String setupToken = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Superday", "August");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateparse_invalidMonth_returns500() {
        String setupToken = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Monday", "Movember");
        act.then().statusCode(200);
    }
}