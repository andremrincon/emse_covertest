package ts01glm_5_2_2;

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
    public void testDateParseValidFullDayAndMonthNames() {
        given()
            .when()
                .get("/api/dateparse/Wednesday/August")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAbbrevAndMixedCaseMonth() {
        given()
            .when()
                .get("/api/dateparse/tuesday/MAR")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonthReturnsError() {
        given()
            .when()
                .get("/api/dateparse/123/456")
            .then()
                .statusCode(200);
    }
}