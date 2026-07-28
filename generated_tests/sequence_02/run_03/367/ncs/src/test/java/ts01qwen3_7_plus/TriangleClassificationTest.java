package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testTriangleInvalidA() {
        given()
            .pathParam("a", 0)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateral() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInvalidMaxA() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInvalidMaxB() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 3)
            .pathParam("c", 1)
        .when()
            .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleIsosceles() {
        given()
            .pathParam("a", 2)
            .pathParam("b", 2)
            .pathParam("c", 3)
        .when()
            .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleScalene() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }
}