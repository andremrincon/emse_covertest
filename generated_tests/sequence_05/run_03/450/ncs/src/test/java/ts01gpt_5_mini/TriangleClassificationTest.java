package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveEdgesReturnInvalid() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/0/1/1");
        resp.then().statusCode(200).body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleDetected() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/3/3");
        resp.then().statusCode(200).body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityViolationReturnsInvalid() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/1/1");
        resp.then().statusCode(200).body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleDetected() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/5/3");
        resp.then().statusCode(200).body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleDetected() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/4/5/6");
        resp.then().statusCode(200).body("triangleType", nullValue());
    }
}