package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    private String base() {
        String b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = System.getProperty("api.base", "http://localhost:8080");
        return b;
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidNegativeX_Returns400() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base() + "/api/expint/{n}/{x}", 3, -1.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NIsZero_Returns200() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base() + "/api/expint/{n}/{x}", 0, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XIsZeroAndNIsOne_Returns400() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base() + "/api/expint/{n}/{x}", 1, 0.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFractionPath_Returns200() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base() + "/api/expint/{n}/{x}", 3, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesPath_NonZeroNm1_Returns200() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base() + "/api/expint/{n}/{x}", 3, 0.1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesPath_Nm1Zero_Returns200() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base() + "/api/expint/{n}/{x}", 1, 0.1);
        act.then().statusCode(200);
    }
}