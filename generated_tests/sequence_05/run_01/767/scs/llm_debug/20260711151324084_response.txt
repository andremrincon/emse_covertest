package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testValidDayAndValidMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDay() {
        given()
            .when()
                .get("/api/dateparse/Superday/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/Movember")
            .then()
                .statusCode(200);
    }
}