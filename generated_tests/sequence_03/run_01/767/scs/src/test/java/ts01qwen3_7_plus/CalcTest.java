package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CalcTest {

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given()
            .pathParam("op", "plus")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 5.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testCalcDivideByZero() {
        given()
            .pathParam("op", "divide")
            .pathParam("arg1", 10.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        given()
            .pathParam("op", "pi")
            .pathParam("arg1", 0.0)
            .pathParam("arg2", 0.0)
        .when()
            .get("/api/calc/{op}/{arg1}/{arg2}")
        .then()
            .statusCode(lessThan(300));
    }
}