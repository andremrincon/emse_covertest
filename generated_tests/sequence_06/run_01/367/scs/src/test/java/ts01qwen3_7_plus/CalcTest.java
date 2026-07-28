package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    static {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCalcPiConstant() {
        given()
            .pathParam("op", "pi")
            .pathParam("arg1", 0)
            .pathParam("arg2", 0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200)
            .body(equalTo(String.valueOf(Math.PI)));
    }

    @Test(timeout = 60000)
    public void testCalcSqrtUnary() {
        given()
            .pathParam("op", "sqrt")
            .pathParam("arg1", 16)
            .pathParam("arg2", 0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200)
            .body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testCalcPlusBinary() {
        given()
            .pathParam("op", "plus")
            .pathParam("arg1", 5)
            .pathParam("arg2", 3)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200)
            .body(equalTo("8.0"));
    }
}