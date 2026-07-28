package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class CalcTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCalcLog() {
        RestAssured.given()
                .when()
                .get("/api/calc/log/10.0/1.0")
                .then()
                .statusCode(200);
    }
}