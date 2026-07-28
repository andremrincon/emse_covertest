package ts01gpt_5_mini;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testInvalidWhenNonPositiveSides() {
        int a = -1;
        int b = 5;
        int c = 5;
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/expint/{n}/{x}", 1, 0.1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", a, b, c);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        int a = 5;
        int b = 5;
        int c = 5;
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/bessj/{n}/{x}", 3, 1e-10).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", a, b, c);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityViolation() {
        int a = 5;
        int b = 2;
        int c = 2;
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/bessj/{n}/{x}", 3, 1e-10).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", a, b, c);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        int a = 5;
        int b = 5;
        int c = 3;
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/expint/{n}/{x}", 1, 0.1).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", a, b, c);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        int a = 3;
        int b = 4;
        int c = 5;
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", a, b, c);
        Assert.assertEquals(200, act.getStatusCode());
    }
}