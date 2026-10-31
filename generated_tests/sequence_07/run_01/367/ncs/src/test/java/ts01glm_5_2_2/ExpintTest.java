package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintErrorNNegative() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", -1, 2.5)
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 0, 2.5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNGreaterThanOne() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 2, 0.0)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 3, 2.5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1NotZero() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 3, 0.1)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1Zero() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 1, 0.1)
            .then()
                .statusCode(200);
    }
}