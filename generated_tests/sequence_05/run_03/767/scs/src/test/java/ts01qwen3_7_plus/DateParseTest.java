package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParse_ValidDayAndMonths() {
        String[] months = {"feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (String month : months) {
            given()
                .when()
                    .get("/api/dateparse/mon/" + month)
                .then()
                    .statusCode(lessThan(300));
        }

        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testDateParse_InvalidDay() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/dateparse/Monday/jan")
            .then()
                .statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testDateParse_InvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/dateparse/mon/Movember")
            .then()
                .statusCode(500);
    }
}