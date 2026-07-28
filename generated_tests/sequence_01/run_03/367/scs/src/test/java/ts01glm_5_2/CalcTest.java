package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcConstantOperatorPi() {
        given()
            .when()
                .get("/api/calc/pi/0/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcBinaryOperatorPlus() {
        given()
            .when()
                .get("/api/calc/plus/15.5/4.5")
            .then()
                .body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testCalcUnaryOperatorSqrt() {
        given()
            .when()
                .get("/api/calc/sqrt/16/0")
            .then()
                .body(equalTo("4.0"));
    }
}