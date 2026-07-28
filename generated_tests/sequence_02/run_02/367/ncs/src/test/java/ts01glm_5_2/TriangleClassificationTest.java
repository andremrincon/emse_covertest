package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host");
        if (host == null || host.isEmpty()) {
            host = System.getenv("SERVER_HOST");
        }
        if (host == null || host.isEmpty()) {
            host = "localhost";
        }
        String port = System.getProperty("server.port");
        if (port == null || port.isEmpty()) {
            port = System.getenv("SERVER_PORT");
        }
        if (port == null || port.isEmpty()) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleWithNonPositiveFirstEdge() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200)
                .body("resultAsInt", equalTo(0));
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleWithNonPositiveSecondEdge() {
        given()
            .when()
                .get("/api/triangle/3/-1/5")
            .then()
                .statusCode(200)
                .body("resultAsInt", equalTo(0));
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/1/1/1")
            .then()
                .statusCode(200)
                .body("resultAsInt", equalTo(3));
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangle() {
        given()
            .when()
                .get("/api/triangle/2/1/1")
            .then()
                .statusCode(200)
                .body("resultAsInt", equalTo(0));
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200)
                .body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200)
                .body("resultAsInt", equalTo(1));
    }
}