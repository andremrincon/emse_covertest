package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static String base() {
        String b = System.getProperty("API_BASE");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testNegativeSideReturnsInvalidStatusCode200() {
        String base = base();
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/triangle/-1/5/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroSideReturnsInvalidStatusCode200() {
        String base = base();
        given().when().get(base + "/api/remainder/10/3").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/triangle/0/4/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralReturnsStatus200() {
        String base = base();
        given().when().get(base + "/api/remainder/9/4").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/triangle/5/5/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturnsStatus200() {
        String base = base();
        given().when().get(base + "/api/remainder/8/3").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/triangle/1/2/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesReturnsStatus200() {
        String base = base();
        given().when().get(base + "/api/remainder/11/6").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/triangle/5/5/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneReturnsStatus200() {
        String base = base();
        given().when().get(base + "/api/remainder/7/2").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/triangle/3/4/5");
        resp.then().statusCode(200);
    }
}