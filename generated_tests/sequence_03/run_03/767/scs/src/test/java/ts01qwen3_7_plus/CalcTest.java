package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CalcTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test(timeout = 60000)
    public void testSine() {
        given()
            .pathParam("op", "sine")
            .pathParam("arg1", 1.5)
            .pathParam("arg2", 0.0)
        .when()
            .get(BASE_URL + "/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTangent() {
        given()
            .pathParam("op", "tangent")
            .pathParam("arg1", 1.0)
            .pathParam("arg2", 0.0)
        .when()
            .get(BASE_URL + "/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlus() {
        given()
            .pathParam("op", "plus")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 5.0)
        .when()
            .get(BASE_URL + "/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMultiply() {
        given()
            .pathParam("op", "multiply")
            .pathParam("arg1", 4.0)
            .pathParam("arg2", 2.5)
        .when()
            .get(BASE_URL + "/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivide() {
        given()
            .pathParam("op", "divide")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 2.0)
        .when()
            .get(BASE_URL + "/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }
}