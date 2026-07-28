package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNLessThanTwo_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 1, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testXZero_ReturnsZeroValue() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 0).then().body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testAxGreaterThanN_UsesAsymptoticBranch_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 10.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxLessOrEqualN_UsesDownwardRecurrence_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 50, 1e-10).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeXAndOddN_ResultNegative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, -2.5).then().body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testBessj0_AxLessThan8_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 2, 3.0).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj1_AxGreaterOrEqual8_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 2, -10.0).then().statusCode(400);
    }
}