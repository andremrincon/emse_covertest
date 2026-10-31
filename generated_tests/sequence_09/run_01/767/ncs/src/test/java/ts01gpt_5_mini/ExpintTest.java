package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContinuedFractionBranch_expint_n2_x2point5_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/2/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeriesBranch_expint_n3_x0point1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNZeroBranch_expint_n0_x2point5_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/0/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXZeroNMoreThanOne_expint_n3_x0point0_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeN_expint_nMinus1_x0point1_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/-1/0.1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeX_expint_n1_xMinus0point1_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/1/-0.1").then().statusCode(400);
    }
}