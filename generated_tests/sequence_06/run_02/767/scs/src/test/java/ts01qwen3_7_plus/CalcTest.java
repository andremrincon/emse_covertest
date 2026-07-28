package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("op", "plus")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 5.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("op", "sqrt")
            .pathParam("arg1", 16.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCalcInvalidOp() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("op", "power")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 2.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }
}