package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonthReturns200() {
        given()
            .when()
                .get("/api/dateparse/Wednesday/August")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonthReturns500() {
        given()
            .when()
                .get("/api/dateparse/Superday/Movember")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseNumericInputReturns500() {
        given()
            .when()
                .get("/api/dateparse/123/456")
            .then()
                .statusCode(200);
    }
}