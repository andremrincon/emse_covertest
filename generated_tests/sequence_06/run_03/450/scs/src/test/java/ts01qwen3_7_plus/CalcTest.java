package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CalcTest {

    @Test(timeout = 60000)
    public void testCalcInit() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;

        given()
                .pathParam("op", "plus")
                .pathParam("arg1", 15.5)
                .pathParam("arg2", 4.5)
                .when()
                .get("/api/calc/{op}/{arg1}/{arg2}")
                .then()
                .statusCode(200);
    }
}