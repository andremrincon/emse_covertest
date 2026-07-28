package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {
    private static String BASE;

    @BeforeClass
    public static void init() {
        String p = System.getProperty("api.base");
        if (p == null || p.isEmpty()) p = System.getenv("API_BASE");
        if (p == null || p.isEmpty()) p = "http://localhost:8080";
        BASE = p;
    }

    @Test(timeout = 60000)
    public void test_dateparse_mon_jan_returns_two() {
        given().when().get(BASE + "/api/pat/TheQuickBrownFox").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/dateparse/Mon/Jan");
        String body = resp.getBody().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void test_dateparse_superday_movember_returns_500() {
        given().when().get(BASE + "/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/dateparse/Superday/Movember");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_dateparse_tuesday_MAR_case_insensitive_returns_four() {
        given().when().get(BASE + "/api/pat/ArrangeCall").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/dateparse/tuesday/MAR");
        String body = resp.getBody().asString();
        assertEquals("3", body);
    }
}