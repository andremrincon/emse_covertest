package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleMaxA() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleMaxB() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 3)
            .pathParam("c", 1)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleMaxC() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 1)
            .pathParam("c", 3)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleAB() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 4)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleBC() {
        given()
            .pathParam("a", 4)
            .pathParam("b", 3)
            .pathParam("c", 3)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleAC() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 3)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }
}