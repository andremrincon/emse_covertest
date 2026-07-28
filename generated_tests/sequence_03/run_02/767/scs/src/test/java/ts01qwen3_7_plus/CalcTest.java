package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcLog() {
        given()
            .when()
                .get("/api/calc/log/15.5/4.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcCosine() {
        given()
            .when()
                .get("/api/calc/cosine/0.0/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcTangent() {
        given()
            .when()
                .get("/api/calc/tangent/0.0/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSubtract() {
        given()
            .when()
                .get("/api/calc/subtract/15.5/4.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcMultiply() {
        given()
            .when()
                .get("/api/calc/multiply/15.5/4.5")
            .then()
                .statusCode(200);
    }
}