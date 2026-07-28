package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testPlusOperation() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/calc/plus/10.0/5.0")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSqrtOperation() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/calc/sqrt/16.0/0.0")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDivideByZero() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/calc/divide/10.0/0.0")
        .then()
            .statusCode(200);
    }
}