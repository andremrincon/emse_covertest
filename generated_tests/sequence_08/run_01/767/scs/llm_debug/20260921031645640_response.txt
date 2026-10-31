package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CalcTest {

    static {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCalcAdd() {
        given()
            .pathParam("op", "add")
            .pathParam("arg1", 15.5)
            .pathParam("arg2", 4.5)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSubtract() {
        given()
            .pathParam("op", "subtract")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 5.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
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
            .statusCode(200);
    }
}