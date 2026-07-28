package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testExpintN0X25() {
        given()
                .when()
                .get("/api/expint/0/2.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN2X0() {
        given()
                .when()
                .get("/api/expint/2/0.0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN1X25() {
        given()
                .when()
                .get("/api/expint/1/2.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN1X05() {
        given()
                .when()
                .get("/api/expint/1/0.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN2X05() {
        given()
                .when()
                .get("/api/expint/2/0.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidN() {
        given()
                .when()
                .get("/api/expint/-1/1.0")
                .then()
                .statusCode(400);
    }
}