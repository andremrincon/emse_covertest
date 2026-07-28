package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintN3X0() {
        given()
            .when()
                .get("/api/expint/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN3X2_5() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN3X0_5() {
        given()
            .when()
                .get("/api/expint/3/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN1X0_5() {
        given()
            .when()
                .get("/api/expint/1/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN0X2_5() {
        given()
            .when()
                .get("/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintError() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }
}