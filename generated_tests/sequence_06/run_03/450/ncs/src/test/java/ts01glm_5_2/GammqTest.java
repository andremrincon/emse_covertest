package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqGserNormalPath() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPath() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserXZero() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegative() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegative() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAType() {
        given()
            .when()
                .get("/api/gammq/abc/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserSmallA() {
        given()
            .when()
                .get("/api/gammq/0.5/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeX() {
        given()
            .when()
                .get("/api/gammq/3.0/50.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserBoundary() {
        given()
            .when()
                .get("/api/gammq/2.0/2.9")
            .then()
                .statusCode(200);
    }
}