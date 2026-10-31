package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
                .when()
                .get("/api/calc/plus/15.5/4.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivide() {
        given()
                .when()
                .get("/api/calc/divide/10.0/2.0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        given()
                .when()
                .get("/api/calc/pi/0.0/0.0")
                .then()
                .statusCode(200);
    }
}