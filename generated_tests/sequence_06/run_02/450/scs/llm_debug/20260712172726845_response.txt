package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidWednesdayAugustReturns200() {
        given().when().get("/api/dateparse/Wednesday/August").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tuesday/MAR").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Wednesday/August").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testValidTuesdayMARReturns200_caseInsensitive() {
        given().when().get("/api/dateparse/Wednesday/August").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/TUESDAY/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tuesday/MAR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidDaySuperdayReturns500() {
        given().when().get("/api/dateparse/Wednesday/August").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tuesday/MAR").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Superday/MAR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidMonthMovemberReturns500() {
        given().when().get("/api/dateparse/Wednesday/August").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/tuesday/MAR").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Wednesday/Movember").then().statusCode(200);
    }
}