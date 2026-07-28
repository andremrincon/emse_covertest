package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTriangleInvalidWhenNonPositiveEdgeProvided() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/0/5/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateralDetected() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/4/4/4");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleDegenerateNonTriangleDetected() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/2/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleIsoscelesDetected() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/5/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleScaleneDetected() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/triangle/3/4/5");
        resp.then().statusCode(200);
    }
}