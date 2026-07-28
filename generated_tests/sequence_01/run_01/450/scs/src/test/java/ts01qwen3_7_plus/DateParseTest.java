package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    static {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testValidDayAndValidMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndValidMonth() {
        given()
            .when()
                .get("/api/dateparse/xyz/feb")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testValidDayAndInvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/tue/xyz")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndInvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/xyz/xyz")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testAllDaysOfWeek() {
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
    public void testAllMonthsOfYear() {
        String[] months = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (String month : months) {
            given()
                .when()
                    .get("/api/dateparse/mon/" + month)
                .then()
                    .statusCode(200);
        }
    }
}