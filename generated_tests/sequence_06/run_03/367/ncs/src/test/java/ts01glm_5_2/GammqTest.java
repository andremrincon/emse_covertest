package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testGcfPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/1000.0")
        .then()
            .statusCode(200);
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
    public void testGserWithXZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidANegative() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/-1.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidXNegative() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeAZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidTypeA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidTypeX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/abc")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserNonConvergence() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/1000.0/1000.5")
        .then()
            .statusCode(400);
    }
}