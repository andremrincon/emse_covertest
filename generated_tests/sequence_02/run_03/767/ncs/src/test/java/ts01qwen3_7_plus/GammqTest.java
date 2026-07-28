package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void test_exe_invalid_a_negative() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_exe_invalid_a_zero() {
        given()
            .when()
                .get("/api/gammq/0.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_exe_invalid_x_negative() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_gser_x_zero() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gser_normal() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gcf_normal() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gser_boundary() {
        given()
            .when()
                .get("/api/gammq/100.0/100.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gcf_boundary() {
        given()
            .when()
                .get("/api/gammq/100.0/101.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gammq_invalid_a_string() {
        given()
            .when()
                .get("/api/gammq/abc/2.0")
            .then()
                .statusCode(400);
    }
}