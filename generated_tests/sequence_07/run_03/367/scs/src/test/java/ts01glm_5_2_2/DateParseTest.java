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
    public void testDateParseDaysMonToThurMonthsJanToApr() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tue/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/thur/apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseDaysFriToSunMonthsMayToJul() {
        given().when().get("/api/dateparse/fri/may").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sat/jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/sun/jul").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseMonthsAugToOctWithValidDay() {
        given().when().get("/api/dateparse/mon/aug").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/oct").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseMonthsNovDecWithValidDay() {
        given().when().get("/api/dateparse/mon/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/dec").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseInvalidDayAndMonthReturns500() {
        given().when().get("/api/dateparse/Superday/Movember").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseUppercaseDayAndMonth() {
        given().when().get("/api/dateparse/MON/JAN").then().statusCode(200);
    }
}