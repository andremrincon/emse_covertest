package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testValidDayWedAndMonthAug() {
        given()
            .when()
                .get("/api/dateparse/Wednesday/August")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testValidDayTueAndMonthMar() {
        given()
            .when()
                .get("/api/dateparse/tuesday/MAR")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testValidDayFriAndMonthJan() {
        given()
            .when()
                .get("/api/dateparse/Friday/January")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testValidDayThurAndMonthJun() {
        given()
            .when()
                .get("/api/dateparse/Thursday/June")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testValidDaySatAndMonthOct() {
        given()
            .when()
                .get("/api/dateparse/Saturday/October")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndInvalidMonth() {
        given()
            .when()
                .get("/api/dateparse/xyz/xyz")
            .then()
                .statusCode(200);
    }
}