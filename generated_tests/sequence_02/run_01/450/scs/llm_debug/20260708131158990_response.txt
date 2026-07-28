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
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDayAndMonthAbbreviationsProduceSum() {
        given().when().get("/api/pat/TheQuickBrownFox").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/mon/jan");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testEndOfYearWithWeekdayAbbreviation() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/sun/dec");
        assertEquals("13", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnknownDayWithKnownMonth() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/noday/mar");
        assertEquals("3", act.getBody().asString());
    }
}