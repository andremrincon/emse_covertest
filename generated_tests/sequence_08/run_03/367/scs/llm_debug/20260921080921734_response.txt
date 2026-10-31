package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Test(timeout = 60000)
    public void testAllValidMonths() {
        String[] months = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (String month : months) {
            given()
                .when()
                    .get("/api/dateparse/mon/" + month)
                .then()
                    .statusCode(lessThan(300));
        }
    }

    @Test(timeout = 60000)
    public void testAllValidDays() {
        String[] days = {"mon", "tue", "wed", "thur", "fri", "sat", "sun"};
        for (String day : days) {
            given()
                .when()
                    .get("/api/dateparse/" + day + "/jan")
                .then()
                    .statusCode(lessThan(300));
        }
    }

    @Test(timeout = 60000)
    public void testInvalidDayWithValidMonth() {
        given()
            .when()
                .get("/api/dateparse/superday/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayWithInvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/movember")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDayWithInvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/superday/movember")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNumericParameters() {
        given()
            .when()
                .get("/api/dateparse/123/456")
            .then()
                .statusCode(200);
    }
}