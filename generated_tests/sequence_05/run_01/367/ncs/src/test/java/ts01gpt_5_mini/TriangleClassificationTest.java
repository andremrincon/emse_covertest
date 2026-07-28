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
    public void testTriangleInvalidNonPositive() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response response = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleDegenerateEquality() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response response = given().when().get("/api/triangle/{a}/{b}/{c}", 1, 2, 3);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateral() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response response = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleIsosceles() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response response = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 8);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleScalene() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response response = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        response.then().statusCode(200);
    }
}