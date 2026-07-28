package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFraction_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesPath_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 0.1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NNotZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 0.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", -1, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NZero_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 0.0).then().statusCode(400);
    }
}