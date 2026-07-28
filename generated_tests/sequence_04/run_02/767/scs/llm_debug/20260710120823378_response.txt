package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndJanuary() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jan")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndFebruary() {
        given()
            .pathParam("dayname", "tue")
            .pathParam("monthname", "feb")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayValidMonth() {
        given()
            .pathParam("dayname", "invalid")
            .pathParam("monthname", "mar")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayInvalidMonth() {
        given()
            .pathParam("dayname", "wed")
            .pathParam("monthname", "invalid")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayInvalidMonth() {
        given()
            .pathParam("dayname", "superday")
            .pathParam("monthname", "movember")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseAnotherValidDayAndMonth() {
        given()
            .pathParam("dayname", "thur")
            .pathParam("monthname", "apr")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(200);
    }
}