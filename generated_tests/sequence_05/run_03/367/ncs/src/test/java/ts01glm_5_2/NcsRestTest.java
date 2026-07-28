package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessj_n3_x0_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 0.0)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_n3_x2_5_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_n2_returns400() {
        given()
                .pathParam("n", 2)
                .pathParam("x", 2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_n1001_returns400() {
        given()
                .pathParam("n", 1001)
                .pathParam("x", 2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_m10_n5_x0_returns200() {
        given()
                .pathParam("m", 10)
                .pathParam("n", 5)
                .pathParam("x", 0.0)
        .when()
                .get("/api/fisher/{m}/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m10_n5_x0_75_returns200() {
        given()
                .pathParam("m", 10)
                .pathParam("n", 5)
                .pathParam("x", 0.75)
        .when()
                .get("/api/fisher/{m}/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m1001_returns400() {
        given()
                .pathParam("m", 1001)
                .pathParam("n", 5)
                .pathParam("x", 0.5)
        .when()
                .get("/api/fisher/{m}/{n}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_a5_5_x2_3_returns200() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", 2.3)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_a0_001_x1000_returns200() {
        given()
                .pathParam("a", 0.001)
                .pathParam("x", 1000.0)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_a17_b5_returns200() {
        given()
                .pathParam("a", 17)
                .pathParam("b", 5)
        .when()
                .get("/api/remainder/{a}/{b}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_aNeg9_b5_returns200() {
        given()
                .pathParam("a", -9)
                .pathParam("b", 5)
        .when()
                .get("/api/remainder/{a}/{b}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_a10001_returns400() {
        given()
                .pathParam("a", 10001)
                .pathParam("b", 5)
        .when()
                .get("/api/remainder/{a}/{b}")
        .then()
                .statusCode(400);
    }
}