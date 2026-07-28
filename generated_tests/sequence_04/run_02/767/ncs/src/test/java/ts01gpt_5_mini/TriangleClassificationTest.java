package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTriangleWithNonPositiveEdgeReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/0/5/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/3/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/2/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/2/2/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/4/5");
        resp.then().statusCode(200);
    }
}