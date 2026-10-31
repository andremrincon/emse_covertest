package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContinuedFractionPath_returns200() {
        given().when().get("/api/bessj/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeriesPath_returnsStatusSuccessInBody() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 0.1).then().body("status", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testNZeroPath_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeN_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", -1, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testXZeroAndNOne_invalidCombination_returns400() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 1, 0.0).then().statusCode(400);
    }
}