package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL_HOST");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 3, 3);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        res.then().statusCode(200);
    }
}