package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CalcTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        String baseUrl = getBaseUrl();
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/calc/plus/10.0/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        String baseUrl = getBaseUrl();
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/calc/sqrt/16.0/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        String baseUrl = getBaseUrl();
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/calc/pi/0.0/0.0")
        .then()
            .statusCode(200);
    }
}