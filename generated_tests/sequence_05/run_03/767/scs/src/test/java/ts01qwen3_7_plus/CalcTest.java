package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CalcTest {

    @Test(timeout = 60000)
    public void testLogOperation() {
        given()
            .baseUri(System.getProperty("baseUrl", "http://localhost:8080"))
            .pathParam("op", "log")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(200);
    }
}