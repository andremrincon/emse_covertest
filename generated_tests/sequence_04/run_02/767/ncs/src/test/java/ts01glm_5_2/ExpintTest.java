package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testExpintErrorNegativeN() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroXNonZero() {
        given()
            .when()
                .get("/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNGreaterThanOne() {
        given()
            .when()
                .get("/api/expint/3/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathNm1NotZero() {
        given()
            .when()
                .get("/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathNm1Zero() {
        given()
            .when()
                .get("/api/expint/1/0.1")
            .then()
                .statusCode(200);
    }
}