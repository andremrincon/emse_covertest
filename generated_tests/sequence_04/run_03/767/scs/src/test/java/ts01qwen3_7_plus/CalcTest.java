package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CalcTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testConstantOperation() {
        given()
            .pathParam("op", "pi")
            .pathParam("arg1", 0)
            .pathParam("arg2", 0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryOperation() {
        given()
            .pathParam("op", "sqrt")
            .pathParam("arg1", 16)
            .pathParam("arg2", 0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperation() {
        given()
            .pathParam("op", "plus")
            .pathParam("arg1", 10)
            .pathParam("arg2", 5)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }
}