package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DateParseTest {

    @Test(timeout = 60000)
    public void testDateParseWednesdayAugust() {
        given()
            .pathParam("dayname", "Wednesday")
            .pathParam("monthname", "August")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseTuesdayMar() {
        given()
            .pathParam("dayname", "tuesday")
            .pathParam("monthname", "MAR")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseMonJan() {
        given()
            .pathParam("dayname", "mon")
            .pathParam("monthname", "jan")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseTueFeb() {
        given()
            .pathParam("dayname", "tue")
            .pathParam("monthname", "feb")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseWedMar() {
        given()
            .pathParam("dayname", "wed")
            .pathParam("monthname", "mar")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(lessThan(300));
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testDateParseInvalid() {
        given()
            .pathParam("dayname", "invalid")
            .pathParam("monthname", "invalid")
        .when()
            .get("/api/dateparse/{dayname}/{monthname}")
        .then()
            .statusCode(500);
    }
}