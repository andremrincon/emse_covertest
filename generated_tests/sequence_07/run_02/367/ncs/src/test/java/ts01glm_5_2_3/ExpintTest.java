package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNError() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", -1, 2.5)
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintN0Branch() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 0, 2.5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroN2Branch() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 2, 0.0)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1Branch() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 3, 2.5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathNm1Zero() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 1, 0.1)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathWithPsi() {
        given()
            .when()
                .get("/api/expint/{n}/{x}", 3, 0.1)
            .then()
                .statusCode(200);
    }
}