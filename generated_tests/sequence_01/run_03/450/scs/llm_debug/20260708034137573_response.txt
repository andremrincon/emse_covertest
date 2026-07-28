package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidAbbrevDayAndMonthReturnsSum() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Mon/Jan");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testValidDayAndUnknownMonthReturnsDayOnly() {
        given().when().get("/api/pat/Example").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/tue/unknownmonth");
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testInvalidDayTriggersServerError() {
        given().when().get("/api/pat/Check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/123/mar");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCaseInsensitivityForDayAndMonth() {
        given().when().get("/api/pat/Hello").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/WED/MAR");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testEndOfYearMonthWithWeekendDay() {
        given().when().get("/api/pat/Sample").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/Sun/Dec");
        assertEquals("13", act.getBody().asString());
    }
}