package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv() != null && System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPositiveAPositiveB_Status200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositiveANegativeB_Status200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, -9);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeAPositiveB_Status200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -17, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeANegativeB_Status200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -17, -5);
        resp.then().statusCode(200);
    }
}