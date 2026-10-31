package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DateParseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testValidDays() {
        String[] days = {"mon", "tue", "wed", "thur", "fri", "sat", "sun"};
        for (String day : days) {
            given()
                .when()
                .get("/api/dateparse/" + day + "/jan")
                .then()
                .statusCode(200);
        }
    }

    @Test(timeout = 60000)
    public void testMonthsFirstHalf() {
        String[] months = {"jan", "feb", "mar", "apr"};
        for (String month : months) {
            given()
                .when()
                .get("/api/dateparse/mon/" + month)
                .then()
                .statusCode(200);
        }
    }

    @Test(timeout = 60000)
    public void testMonthsSecondHalf() {
        String[] months = {"may", "jun", "jul", "aug"};
        for (String month : months) {
            given()
                .when()
                .get("/api/dateparse/mon/" + month)
                .then()
                .statusCode(200);
        }
    }

    @Test(timeout = 60000)
    public void testMonthsThirdQuarter() {
        String[] months = {"sep", "oct", "nov", "dec"};
        for (String month : months) {
            given()
                .when()
                .get("/api/dateparse/mon/" + month)
                .then()
                .statusCode(200);
        }
    }

    @Test(timeout = 60000)
    public void testInvalidDayValidMonth() {
        given()
            .when()
            .get("/api/dateparse/xyz/jan")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidDayInvalidMonth() {
        given()
            .when()
            .get("/api/dateparse/mon/xyz")
            .then()
            .statusCode(200);
    }
}