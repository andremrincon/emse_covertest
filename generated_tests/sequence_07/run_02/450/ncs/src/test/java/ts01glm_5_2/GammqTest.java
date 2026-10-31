package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/-1.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNonNumericA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfBoundary() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.0/6.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserITMAXExceeded() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/10000000000.0/10000000000.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfITMAXExceeded() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/10000000000.0/10000000001.0")
        .then()
            .statusCode(400);
    }
}