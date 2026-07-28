package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExeInvalidXNegative() {
        given()
            .when()
                .get("/api/gammq/1.0/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidANegative() {
        given()
            .when()
                .get("/api/gammq/-1.0/1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidAZero() {
        given()
            .when()
                .get("/api/gammq/0.0/1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeGserPath() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeGcfPath() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .when()
                .get("/api/gammq/1.0/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserLargeA() {
        given()
            .when()
                .get("/api/gammq/150.0/149.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfLargeA() {
        given()
            .when()
                .get("/api/gammq/150.0/151.0")
            .then()
                .statusCode(200);
    }
}