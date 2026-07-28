package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ExpintTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("NCS_BASE");
        if (cfg == null || cfg.isEmpty()) cfg = System.getenv("NCS_BASE");
        if (cfg == null || cfg.isEmpty()) cfg = "http://localhost:8080";
        base = cfg.replaceAll("/$", "");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/expint/{n}/{x}", 3, 2.5);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_XLessThanOrEqualOne_seriesPath_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/expint/{n}/{x}", 3, 0.1);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_computation_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/expint/{n}/{x}", 0, 2.5);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/expint/{n}/{x}", 2, 0.0);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/expint/{n}/{x}", -1, 1.0);
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NEqualOne_returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/expint/{n}/{x}", 1, 0.0);
        assertEquals(400, res.getStatusCode());
    }
}