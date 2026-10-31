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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidEdge_zeroReturns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralReturns200() {
        given().when().get("/api/remainder/{a}/{b}", -9, 4).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 3, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateNonTriangleReturns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 1, 2, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesReturns200() {
        given().when().get("/api/remainder/{a}/{b}", 10, 3).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 3, 4);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneReturns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        resp.then().statusCode(200);
    }
}