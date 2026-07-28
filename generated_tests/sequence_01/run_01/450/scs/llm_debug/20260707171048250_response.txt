package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @Before
    public void setUp() {
        String host = System.getProperty("host", "localhost");
        String port = System.getProperty("port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testDateParseValidDayAndMonth() {
        given()
            .when()
            .get("/api/dateparse/Wednesday/August")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        given()
            .when()
            .get("/api/dateparse/Superday/Movember")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseValidAbbreviations() {
        given()
            .when()
            .get("/api/dateparse/tuesday/MAR")
            .then()
            .statusCode(lessThan(300));
    }
}