package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("test.api.url");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_invalidNegativeN_shouldReturn400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/-1/1.0");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_xZeroAndNZero_shouldReturn400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/0.0");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_nZero_shouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/2.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_seriesPath_shouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0.1");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_continuedFractionPath_shouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/2.5");
        assertEquals(200, act.getStatusCode());
    }
}