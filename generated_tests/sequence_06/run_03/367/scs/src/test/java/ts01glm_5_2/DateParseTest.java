package ts01glm_5_2;

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
    public void testDateParseMonJan() {
        given()
            .when()
                .get("/api/dateparse/mon/jan")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseTueFeb() {
        given()
            .when()
                .get("/api/dateparse/tue/feb")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseWedMar() {
        given()
            .when()
                .get("/api/dateparse/wed/mar")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseThurApr() {
        given()
            .when()
                .get("/api/dateparse/thur/apr")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseFriMay() {
        given()
            .when()
                .get("/api/dateparse/fri/may")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonth() {
        given()
            .when()
                .get("/api/dateparse/123/456")
            .then()
                .statusCode(200);
    }
}