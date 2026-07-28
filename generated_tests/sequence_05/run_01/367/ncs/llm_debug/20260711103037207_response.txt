package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGserNormalExecution() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalExecution() {
        given()
            .pathParam("a", 0.001)
            .pathParam("x", 1000.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfWithExtremelyLargeX() {
        given()
            .pathParam("a", 1.0)
            .pathParam("x", 1e31)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfWithLargeAForITMAX() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 1001.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserWithLargeAForITMAX() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 500.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfWithValuesForLine41() {
        given()
            .pathParam("a", 2.0)
            .pathParam("x", 3.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }
}