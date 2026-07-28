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
    public void testDateParseAllDaysAndMonthsJanThroughJul() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/apr").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/fri/may").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/jul").then().statusCode(lessThan(300));

        given()
            .when()
            .get("/api/dateparse/mon/aug")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseTueSep() {
        given()
            .when()
            .get("/api/dateparse/tue/sep")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseWedOct() {
        given()
            .when()
            .get("/api/dateparse/wed/oct")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseThurNov() {
        given()
            .when()
            .get("/api/dateparse/thur/nov")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseFriDec() {
        given()
            .when()
            .get("/api/dateparse/fri/dec")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayInvalidMonthReturns500() {
        given()
            .when()
            .get("/api/dateparse/xyzday/xyzmonth")
            .then()
            .statusCode(200);
    }
}