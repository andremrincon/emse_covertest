package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @Test(timeout = 60000)
    public void testCalcPi() {
        given()
            .pathParam("op", "pi")
            .pathParam("arg1", 0.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testCalcE() {
        given()
            .pathParam("op", "e")
            .pathParam("arg1", 0.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .body(equalTo("2.718281828459045"));
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        given()
            .pathParam("op", "sqrt")
            .pathParam("arg1", 16.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testCalcSine() {
        given()
            .pathParam("op", "sine")
            .pathParam("arg1", 0.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .body(equalTo("0.0"));
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given()
            .pathParam("op", "plus")
            .pathParam("arg1", 5.0)
            .pathParam("arg2", 3.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .body(equalTo("8.0"));
    }

    @Test(timeout = 60000)
    public void testCalcDivide() {
        given()
            .pathParam("op", "divide")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 2.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .body(equalTo("5.0"));
    }
}