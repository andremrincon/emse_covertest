package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeIndex_Returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", -5, 2.5);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_NonNumericN_Returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/abc/2.5");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_Returns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_LargeX_Returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_BackwardRecurrence_SmallX_LargeN_Returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 20, 1e-10);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeX_OddN_SignHandling_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, -5.0);
        assertEquals(200, resp.getStatusCode());
    }
}