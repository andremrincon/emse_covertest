package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_NLessThan2_Returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_Returns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_UsesForwardRecurrence_Returns200() {
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessOrEqualN_BackwardRecurrence_Returns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 10, 1e-10);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeX_OddN_SignFlip_Returns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        act.then().statusCode(200);
    }
}