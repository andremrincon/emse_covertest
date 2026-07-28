package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGammqValidGser() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidGcf() {
        given()
            .when()
                .get("/api/gammq/1.0/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqXZero() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given()
            .when()
                .get("/api/gammq/1.0/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqZeroA() {
        given()
            .when()
                .get("/api/gammq/0.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserConvergenceFail() {
        given()
            .when()
                .get("/api/gammq/1000.0/999.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfConvergenceFail() {
        given()
            .when()
                .get("/api/gammq/1000.0/1001.0")
            .then()
                .statusCode(200);
    }
}