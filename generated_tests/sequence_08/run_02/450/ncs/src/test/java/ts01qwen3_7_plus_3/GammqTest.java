package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGcfExecution() {
        given()
            .when()
                .get("/api/gammq/1.0/3.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserWithXZero() {
        given()
            .when()
                .get("/api/gammq/1.0/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserWithPositiveX() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserLoopNoConvergence() {
        given()
            .when()
                .get("/api/gammq/10000.0/9999.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeWithNegativeX() {
        given()
            .when()
                .get("/api/gammq/1.0/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeWithNonPositiveA() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }
}