package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFraction_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesN1_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 1, 0.1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNZero_Returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 1.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroWithN2_Returns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 2, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidNegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", -1, 1).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidXZeroN1_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 1, 0).then().statusCode(400);
    }
}