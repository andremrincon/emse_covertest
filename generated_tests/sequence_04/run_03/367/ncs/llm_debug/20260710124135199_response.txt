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
    public void testGcfNormalPath() {
        given()
            .when()
                .get("/api/gammq/5.5/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfFpminBranches() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfItmaxExceeded() {
        given()
            .when()
                .get("/api/gammq/10000.0/10001.0")
            .then()
                .statusCode(400);
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
    public void testGserNormalPath() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserItmaxExceeded() {
        given()
            .when()
                .get("/api/gammq/10000.0/0.5")
            .then()
                .statusCode(200);
    }
}