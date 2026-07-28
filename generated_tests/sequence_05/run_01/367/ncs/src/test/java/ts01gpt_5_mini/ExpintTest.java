package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base.url", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidNegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", -1, 1.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidNegativeX_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 3, -0.5);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 2, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_ContinuedFraction_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XLessOrEqualOne_SeriesConverges_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 3, 0.1);
        act.then().statusCode(200);
    }
}