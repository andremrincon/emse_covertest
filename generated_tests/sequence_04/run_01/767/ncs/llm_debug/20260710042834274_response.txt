package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 1, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_ax_zero_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_ax_greater_than_n_branch_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 10.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_ax_less_or_equal_n_branch_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 10, 1.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_negative_x_odd_n_results_in_negative_value_in_body() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, -2.5).then().body(containsString("-"));
    }

    @Test(timeout = 60000)
    public void testBessj_large_ax_triggers_bessj1_high_ax_branch_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 9.0).then().statusCode(200);
    }
}