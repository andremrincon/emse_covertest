package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/calc/pi/0/0")
        .then()
            .statusCode(200)
            .body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testCalcE() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/calc/e/0/0")
        .then()
            .statusCode(200)
            .body(equalTo("2.718281828459045"));
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/calc/sqrt/16/0")
        .then()
            .statusCode(200)
            .body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testCalcSine() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/calc/sine/0/0")
        .then()
            .statusCode(200)
            .body(equalTo("0.0"));
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/calc/plus/5/3")
        .then()
            .statusCode(200)
            .body(equalTo("8.0"));
    }

    @Test(timeout = 60000)
    public void testCalcDivide() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/calc/divide/10/2")
        .then()
            .statusCode(200)
            .body(equalTo("5.0"));
    }
}