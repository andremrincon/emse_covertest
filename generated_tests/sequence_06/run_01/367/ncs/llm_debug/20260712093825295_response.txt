package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static final String BASE = System.getProperty("base.url", System.getenv("BASE_URL")) != null ? System.getProperty("base.url", System.getenv("BASE_URL")) : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testNonPositiveEdgeResultsInResponse200() {
        int rnd = Math.abs(UUID.randomUUID().hashCode() % 1000) + 1;
        given().when().get(BASE + "/api/remainder/{a}/{b}", rnd, 2).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 0, 5, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralEdgesResultsInResponse200() {
        int rnd = Math.abs(UUID.randomUUID().hashCode() % 1000) + 2;
        given().when().get(BASE + "/api/remainder/{a}/{b}", rnd, 3).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 5, 5, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturnsResponse200() {
        int rnd = Math.abs(UUID.randomUUID().hashCode() % 1000) + 3;
        given().when().get(BASE + "/api/remainder/{a}/{b}", rnd, 4).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 1, 2, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturnsResponse200() {
        int rnd = Math.abs(UUID.randomUUID().hashCode() % 1000) + 4;
        given().when().get(BASE + "/api/remainder/{a}/{b}", rnd, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturnsResponse200() {
        int rnd = Math.abs(UUID.randomUUID().hashCode() % 1000) + 5;
        given().when().get(BASE + "/api/remainder/{a}/{b}", rnd, 6).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        resp.then().statusCode(200);
    }
}