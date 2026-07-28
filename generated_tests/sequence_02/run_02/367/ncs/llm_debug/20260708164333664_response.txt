package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static final String BASE = System.getProperty("API_BASE", System.getenv("API_BASE")) != null
            ? System.getProperty("API_BASE", System.getenv("API_BASE"))
            : "http://localhost:8080";

    @Test(timeout = 60000)
    public void equilateralTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void nonPositiveEdgeReturns200() {
        given().baseUri(BASE).when().get("/api/remainder/{a}/{b}", 8, 3).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void degenerateTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void isoscelesTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void scaleneTriangleReturns200() {
        given().baseUri(BASE).when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}