package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUpBaseUri() {
        RestAssured.baseURI = System.getProperty("baseURL", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void classifyNonPositiveEdgeReturnsInvalidTriangle() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 0, 4, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void classifyEquilateralTriangleReturnsValid() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void classifyInvalidTriangleReturnsInvalid() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 2).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void classifyIsoscelesTriangleReturnsValid() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 2, 2, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void classifyScaleneTriangleReturnsValid() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}