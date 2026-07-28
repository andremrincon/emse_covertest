package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExeInvalidArguments() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 2.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 0.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
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
    public void testGserITMAXTooSmall() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 0.1)
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
    public void testGcfSmallD() {
        given()
            .pathParam("a", 1e-10)
            .pathParam("x", 100.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfSmallC() {
        given()
            .pathParam("a", 10.0)
            .pathParam("x", 100.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfITMAXTooSmall() {
        given()
            .pathParam("a", 1000.0)
            .pathParam("x", 1001.0)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }
}