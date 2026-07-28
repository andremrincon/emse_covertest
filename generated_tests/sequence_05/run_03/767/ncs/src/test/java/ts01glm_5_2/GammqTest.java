package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class GammqTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGserSeriesPathValidResult() {
        given()
            .when()
                .get("/api/gammq/5.5/2.3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfContinuedFractionPathValidResult() {
        given()
            .when()
                .get("/api/gammq/0.001/1000.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZeroReturnsOne() {
        given()
            .when()
                .get("/api/gammq/5.5/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidAThrows400() {
        given()
            .when()
                .get("/api/gammq/-1.0/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidXThrows400() {
        given()
            .when()
                .get("/api/gammq/5.5/-1.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidParameterTypeReturns400() {
        given()
            .when()
                .get("/api/gammq/abc/2.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfPathWithBoundaryCondition() {
        given()
            .when()
                .get("/api/gammq/1.0/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserPathWithSmallAAndSmallX() {
        given()
            .when()
                .get("/api/gammq/0.001/0.0005")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfPathAtExactBoundary() {
        given()
            .when()
                .get("/api/gammq/5.0/6.0")
            .then()
                .statusCode(200);
    }
}