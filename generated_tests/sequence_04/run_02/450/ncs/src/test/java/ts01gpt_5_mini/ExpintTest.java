package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ExpintTest {

    @Before
    public void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", -1, 1.0);
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeX_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 3, -0.5);
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_ZeroXZeroN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 0, 0.0);
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_XPositive_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesPath_SmallX_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 3, 0.1);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFractionPath_LargeX_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        assertEquals(200, res.getStatusCode());
    }
}