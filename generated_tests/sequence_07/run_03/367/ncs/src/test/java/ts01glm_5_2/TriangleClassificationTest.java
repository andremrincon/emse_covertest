package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given().when().get(baseUrl + "/api/triangle/1/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateMaxA() {
        given().when().get(baseUrl + "/api/triangle/3/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateMaxB() {
        given().when().get(baseUrl + "/api/triangle/1/3/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateMaxC() {
        given().when().get(baseUrl + "/api/triangle/1/1/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given().when().get(baseUrl + "/api/triangle/2/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given().when().get(baseUrl + "/api/triangle/3/4/5").then().statusCode(200);
    }
}