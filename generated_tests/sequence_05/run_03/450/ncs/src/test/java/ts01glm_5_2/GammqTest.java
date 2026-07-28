package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
    public void testGserNormalPath_xGreaterThanZeroAndLessThanAPlusOne() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", 2.3)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfPath_xGreaterThanOrEqualToAPlusOne() {
        given()
                .pathParam("a", 0.001)
                .pathParam("x", 1000.0)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXEqualsZero_gamserSetToZero() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", 0.0)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidXNegative_throwsException() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", -1.0)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidAEqualsZero_throwsException() {
        given()
                .pathParam("a", 0.0)
                .pathParam("x", 2.0)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidANegative_throwsException() {
        given()
                .pathParam("a", -1.0)
                .pathParam("x", 2.0)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidParameterTypeForA_returns400() {
        given()
                .pathParam("a", "abc")
                .pathParam("x", 2.0)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidParameterTypeForX_returns400() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", "abc")
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserWithSmallAAndSmallX_coversLoopBody() {
        given()
                .pathParam("a", 0.001)
                .pathParam("x", 0.001)
        .when()
                .get("/api/gammq/{a}/{x}")
        .then()
                .statusCode(200);
    }
}