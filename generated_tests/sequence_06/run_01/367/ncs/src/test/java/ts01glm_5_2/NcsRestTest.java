package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NcsRestTest {

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
    public void testBessj_n3_x0() {
        given()
            .when()
                .get("/api/bessj/3/0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessj_n3_xVerySmall() {
        given()
            .when()
                .get("/api/bessj/3/0.0000000001")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessj_n3_x2_5() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_m10_n5_x0_75() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_m1_n1_x0() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_m1_n1_x0_5() {
        given()
            .when()
                .get("/api/fisher/1/1/0.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammq_a5_5_x2_3() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammq_a0_001_x1000() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammq_a3_x2() {
        given()
            .when()
                .get("/api/gammq/3.0/2.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_a17_b5() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_aNeg9_b4() {
        given()
            .when()
                .get("/api/remainder/-9/4")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainder_a0_b3() {
        given()
            .when()
                .get("/api/remainder/0/3")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}