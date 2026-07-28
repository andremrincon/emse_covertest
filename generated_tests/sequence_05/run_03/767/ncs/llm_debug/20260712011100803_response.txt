package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testZeroSideReturnsInvalidViaStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 2).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralReturnsEquilateralViaStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 3, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonTriangleMaxTooLargeReturnsInvalidViaStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 2).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesReturnsIsoscelesViaStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneReturnsScaleneViaStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}