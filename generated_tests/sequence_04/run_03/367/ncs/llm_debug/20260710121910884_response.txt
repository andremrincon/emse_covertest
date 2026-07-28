package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSidesResultsInHandledResponse() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 3, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        resp.then().statusCode(200);
    }
}