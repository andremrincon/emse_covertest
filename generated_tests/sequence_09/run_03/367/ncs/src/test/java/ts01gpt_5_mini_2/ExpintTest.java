package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", -1, 2.5);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeX_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 1, -0.1);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_ZeroN_PositiveX_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NEquals2_XZero_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 2, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_N3_XGreater1_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_N3_XSmallSeries_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 3, 0.1);
        act.then().statusCode(200);
    }
}