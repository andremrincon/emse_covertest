package ts01glm_5_2;

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
    public void testCalcPlus() {
        given()
            .when()
            .get("/api/calc/plus/15.5/4.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        given()
            .when()
            .get("/api/calc/sqrt/16/0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivide() {
        given()
            .when()
            .get("/api/calc/divide/10/2")
            .then()
            .statusCode(200);
    }
}