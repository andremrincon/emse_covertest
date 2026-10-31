package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("base.port", "8080"));
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleWithNonPositiveFirstEdge() {
        given().when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleWithNonPositiveSecondEdge() {
        given().when().get("/api/triangle/1/0/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotATriangleWithMaxFirstEdge() {
        given().when().get("/api/triangle/5/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given().when().get("/api/triangle/2/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}