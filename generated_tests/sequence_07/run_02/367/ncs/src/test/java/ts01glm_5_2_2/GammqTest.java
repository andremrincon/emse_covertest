package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGcfPathWithLargeX() {
        given()
            .when()
                .get("/api/gammq/5.5/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserPathWithSmallX() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserNonConvergenceThrows() {
        given()
            .when()
                .get("/api/gammq/1000.0/1000.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeA() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeX() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }
}