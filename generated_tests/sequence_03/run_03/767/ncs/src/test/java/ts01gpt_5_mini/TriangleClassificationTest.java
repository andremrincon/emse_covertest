package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidSides_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateral_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonTriangle_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 10, 3, 4);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 4, 5, 6);
        resp.then().statusCode(200);
    }
}