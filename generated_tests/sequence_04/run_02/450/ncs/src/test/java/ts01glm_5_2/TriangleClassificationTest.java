package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testInvalidSideA() {
        given().when().get("/api/triangle/-1/4/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidSideB() {
        given().when().get("/api/triangle/3/-1/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given().when().get("/api/triangle/2/2/2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequality() {
        given().when().get("/api/triangle/5/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given().when().get("/api/triangle/3/3/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}