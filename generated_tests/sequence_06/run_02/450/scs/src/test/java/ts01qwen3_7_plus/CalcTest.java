package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        given()
            .when()
            .get("/api/calc/pi/0/0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        given()
            .when()
            .get("/api/calc/sqrt/16/0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given()
            .when()
            .get("/api/calc/plus/5/10")
            .then()
            .statusCode(200);
    }
}