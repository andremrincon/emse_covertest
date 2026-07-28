package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given()
            .pathParam("op", "plus")
            .pathParam("arg1", 15.5)
            .pathParam("arg2", 4.5)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200)
            .body(equalTo("20.0"));
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
            .statusCode(200)
            .body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        given()
            .pathParam("op", "pi")
            .pathParam("arg1", 0.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200)
            .body(equalTo(String.valueOf(Math.PI)));
    }
}