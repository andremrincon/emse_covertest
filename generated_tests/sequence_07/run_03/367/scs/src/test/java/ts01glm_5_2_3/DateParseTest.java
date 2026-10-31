package ts01glm_5_2_3;

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
    public void testDateParseWithFullDayAndMonthNames_1() {
        given()
            .when()
                .get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWithInvalidDayAndMonthNames_2() {
        given()
            .when()
                .get("/api/dateparse/{dayname}/{monthname}", "Superday", "Movember")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWithMixedCaseMonthName_3() {
        given()
            .when()
                .get("/api/dateparse/{dayname}/{monthname}", "tuesday", "MAR")
            .then()
                .statusCode(200);
    }
}