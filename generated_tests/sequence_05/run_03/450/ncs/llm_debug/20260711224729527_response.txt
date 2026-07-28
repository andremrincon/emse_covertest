package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFraction_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_Series_Nm1Zero_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 1, 0.1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_PositiveX_Returns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 1.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", -1, 1.0).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeX_Returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, -0.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_XZero_Returns400() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 0.0).then().statusCode(400);
    }
}