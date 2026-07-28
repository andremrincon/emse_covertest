package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;

public class ExpintTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", -1, 1.0).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeX_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, -0.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_nZero_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 1.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xGreaterThanOne_continuedFraction_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_seriesBranch_nEquals2_smallX_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 2, 0.1).then().statusCode(200);
    }
}