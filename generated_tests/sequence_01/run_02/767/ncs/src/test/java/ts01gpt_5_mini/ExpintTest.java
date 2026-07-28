package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/expint/{n}/{x}", -1, 1.0);
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne_Returns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/expint/{n}/{x}", 3, 0.0);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_ContinuedFraction_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesPath_NOneSmallX_Returns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 4).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/expint/{n}/{x}", 1, 0.1);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NOne_Returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 2, 3, 4).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/expint/{n}/{x}", 1, 0.0);
        r.then().statusCode(400);
    }
}