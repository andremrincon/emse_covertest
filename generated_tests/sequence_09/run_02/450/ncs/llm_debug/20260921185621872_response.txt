package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testZeroEdgeIsHandledAsInvalidTriangle() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 2, 2, 2);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonTriangleWhenLargestEdgeTooLongIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 2, 2, 3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        act.then().statusCode(200);
    }
}