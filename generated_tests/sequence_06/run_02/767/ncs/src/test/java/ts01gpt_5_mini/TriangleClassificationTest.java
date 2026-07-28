package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static final String BASE = System.getProperty("api.baseUrl") != null ? System.getProperty("api.baseUrl") : (System.getenv("API_BASE_URL") != null ? System.getenv("API_BASE_URL") : "http://localhost:8080");

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testNonPositiveEdgeReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 0, 5, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 5, 5, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 1, 2, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 5, 5, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}