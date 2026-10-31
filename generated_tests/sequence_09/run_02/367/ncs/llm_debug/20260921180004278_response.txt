package ts01gpt_5_mini_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static final String BASE = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testNonPositiveEdgeReturns200() {
        given().baseUri(BASE).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/5/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/3/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/2/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}