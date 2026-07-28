package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidN_LessThan2_Returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 1, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_ZeroX_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 0.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_WithSmallAxBranches_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 4.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_WithLargeAxPositive_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 9.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_WithLargeAxNegative_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, -9.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessOrEqualN_SmallX_LoopPath_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 1.0E-10).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidN_NonNumeric_Returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", "abc", 2.5).then().statusCode(400);
    }
}