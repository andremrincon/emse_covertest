package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testValidFullDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/Wednesday/August")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFullDayAbbreviatedMonth() {
        given()
            .when()
                .get("/api/dateparse/tuesday/MAR")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/Superday/Movember")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAbbreviatedDayMonAndMonthJan() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAbbreviatedDayThurAndMonthApr() {
        given()
            .when()
                .get("/api/dateparse/thur/apr")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAbbreviatedDaySatAndMonthNov() {
        given()
            .when()
                .get("/api/dateparse/sat/nov")
            .then()
                .statusCode(lessThan(300));
    }
}