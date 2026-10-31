package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSine() {
        String op = "sine";
        double arg1 = 15.5;
        double arg2 = 4.5;

        given()
            .pathParam("op", op)
            .pathParam("arg1", arg1)
            .pathParam("arg2", arg2)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCosine() {
        String op = "cosine";
        double arg1 = 15.5;
        double arg2 = 4.5;

        given()
            .pathParam("op", op)
            .pathParam("arg1", arg1)
            .pathParam("arg2", arg2)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTangent() {
        String op = "tangent";
        double arg1 = 15.5;
        double arg2 = 4.5;

        given()
            .pathParam("op", op)
            .pathParam("arg1", arg1)
            .pathParam("arg2", arg2)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMultiply() {
        String op = "multiply";
        double arg1 = 15.5;
        double arg2 = 4.5;

        given()
            .pathParam("op", op)
            .pathParam("arg1", arg1)
            .pathParam("arg2", arg2)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivide() {
        String op = "divide";
        double arg1 = 15.5;
        double arg2 = 4.5;

        given()
            .pathParam("op", op)
            .pathParam("arg1", arg1)
            .pathParam("arg2", arg2)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }
}