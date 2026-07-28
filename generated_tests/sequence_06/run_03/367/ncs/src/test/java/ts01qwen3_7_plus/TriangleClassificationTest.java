package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        return baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle_ZeroSide() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("a", 0)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle_NegativeSide() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("a", 3)
            .pathParam("b", -1)
            .pathParam("c", 4)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 3)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotATriangle_MaxSide() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("a", 3)
            .pathParam("b", 1)
            .pathParam("c", 2)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 4)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }
}