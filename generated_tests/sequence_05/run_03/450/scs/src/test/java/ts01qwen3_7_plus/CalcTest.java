package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CalcTest {

    @Before
    public void setUp() {
        baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCalcLogOperation() {
        given()
                .pathParam("op", "log")
                .pathParam("arg1", 10.0)
                .pathParam("arg2", 2.0)
                .when()
                .get("/api/calc/{op}/{arg1}/{arg2}")
                .then()
                .statusCode(200);
    }
}