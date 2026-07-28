package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseAllMonths() {
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
    public void testDateParseAllDays() {
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
    public void testDateParseInvalidDay() {
        given()
            .when()
                .get("/api/dateparse/xyz/jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/mon/xyz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/xyz/xyz")
            .then()
                .statusCode(200);
    }
}