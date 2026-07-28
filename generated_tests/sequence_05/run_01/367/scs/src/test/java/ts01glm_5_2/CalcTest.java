package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPiConstantOperator() {
        given()
            .pathParam("op", "pi")
            .pathParam("arg1", "0")
            .pathParam("arg2", "0")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEConstantOperator() {
        given()
            .pathParam("op", "e")
            .pathParam("arg1", "0")
            .pathParam("arg2", "0")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtAndLogUnaryOperators() {
        given()
            .pathParam("op", "sqrt")
            .pathParam("arg1", "16")
            .pathParam("arg2", "0")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("op", "log")
            .pathParam("arg1", "2.718281828459045")
            .pathParam("arg2", "0")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTrigOperators() {
        given()
            .pathParam("op", "sine")
            .pathParam("arg1", "0")
            .pathParam("arg2", "0")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("op", "cosine")
            .pathParam("arg1", "0")
            .pathParam("arg2", "0")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("op", "tangent")
            .pathParam("arg1", "0")
            .pathParam("arg2", "0")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryArithmeticOperators() {
        given()
            .pathParam("op", "plus")
            .pathParam("arg1", "15.5")
            .pathParam("arg2", "4.5")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("op", "subtract")
            .pathParam("arg1", "15.5")
            .pathParam("arg2", "4.5")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("op", "multiply")
            .pathParam("arg1", "15.5")
            .pathParam("arg2", "4.5")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideAndDefaultOperator() {
        given()
            .pathParam("op", "divide")
            .pathParam("arg1", "100")
            .pathParam("arg2", "5")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("op", "unknownop")
            .pathParam("arg1", "10")
            .pathParam("arg2", "20")
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }
}