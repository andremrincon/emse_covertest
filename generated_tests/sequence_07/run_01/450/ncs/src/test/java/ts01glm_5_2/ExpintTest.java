package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testExpintN0XPositive() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/{n}/{x}", 0, 2.5)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNGreaterThanOne() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/{n}/{x}", 3, 0.0)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNThrowsError() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/{n}/{x}", -1, 2.5)
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/{n}/{x}", 3, 2.5)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathNm1NotZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/{n}/{x}", 3, 0.1)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathNm1Zero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/{n}/{x}", 1, 0.1)
        .then()
            .statusCode(200);
    }
}