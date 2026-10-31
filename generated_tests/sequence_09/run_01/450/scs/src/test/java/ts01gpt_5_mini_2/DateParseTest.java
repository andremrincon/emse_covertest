package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv().containsKey("BASE_URL") ? System.getenv("BASE_URL") : System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMonJanReturns200() {
        given().when().get("/api/dateparse/mon/jan").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/feb").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/mar").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/mon/apr").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Mon/Jan").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWedMayMixedCaseReturns200() {
        given().when().get("/api/dateparse/WED/may").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/WeD/Jun").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wed/Jul").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/wEd/AuG").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Wed/May").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonMatchingDayMondayDecReturns200() {
        given().when().get("/api/dateparse/Monday/sep").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Monday/oct").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Monday/nov").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Monday/dec").then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/Monday/Dec").then().statusCode(200);
    }
}