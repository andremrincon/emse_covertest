package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testGcfPathWithLargeX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserPathWithXZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/0.0")
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
    public void testInvalidANonNumeric() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/abc/2.0")
        .then()
            .statusCode(400);
    }
}