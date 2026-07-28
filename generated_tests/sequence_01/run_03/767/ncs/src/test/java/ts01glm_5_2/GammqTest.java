package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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
    public void testGserWithZeroX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidANegative() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/-1.0/2.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidXNegative() {
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
            .get("/api/gammq/abc/2.0")
        .then()
            .statusCode(400);
    }
}