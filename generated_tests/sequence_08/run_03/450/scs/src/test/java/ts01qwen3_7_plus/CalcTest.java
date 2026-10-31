package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        given()
            .when()
                .get("/api/calc/pi/0.0/0.0")
            .then()
                .statusCode(200)
                .body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testCalcE() {
        given()
            .when()
                .get("/api/calc/e/0.0/0.0")
            .then()
                .statusCode(200)
                .body(equalTo("2.718281828459045"));
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        given()
            .when()
                .get("/api/calc/sqrt/16.0/0.0")
            .then()
                .statusCode(200)
                .body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given()
            .when()
                .get("/api/calc/plus/5.0/3.0")
            .then()
                .statusCode(200)
                .body(equalTo("8.0"));
    }

    @Test(timeout = 60000)
    public void testCalcDivide() {
        given()
            .when()
                .get("/api/calc/divide/10.0/2.0")
            .then()
                .statusCode(200)
                .body(equalTo("5.0"));
    }

    @Test(timeout = 60000)
    public void testCalcMultiply() {
        given()
            .when()
                .get("/api/calc/multiply/4.0/5.0")
            .then()
                .statusCode(200)
                .body(equalTo("20.0"));
    }
}