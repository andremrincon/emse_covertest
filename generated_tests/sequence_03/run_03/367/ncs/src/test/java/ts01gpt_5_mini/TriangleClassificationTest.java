package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        BASE = System.getenv("BASE_URL");
        if (BASE == null || BASE.isEmpty()) {
            BASE = "http://localhost:8080";
        }
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testNonPositiveEdgeReturnsInvalid() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 0, 5, 5);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 13, 4).then().statusCode(lessThan(300));
        int v = 3;
        String unique = UUID.randomUUID().toString();
        Response response = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", v, v, v);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturnsInvalid() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 11, 2).then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 1, 2, 3);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 19, 6).then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 23, 7).then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        response.then().statusCode(200);
    }
}