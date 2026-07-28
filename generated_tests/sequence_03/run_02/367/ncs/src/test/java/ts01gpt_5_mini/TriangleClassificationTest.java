package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        BASE = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideReturns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 18, 7).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 3, 3, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityViolationReturns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 19, 6).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 5, 1, 1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 20, 6).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 4, 4, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 21, 6).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        resp.then().statusCode(200);
    }
}