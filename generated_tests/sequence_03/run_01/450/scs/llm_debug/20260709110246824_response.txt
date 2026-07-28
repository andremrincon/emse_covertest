package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL", System.getProperty("base.url", "http://localhost:8080"));
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidExampleReturns200() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDayShortAndJanReturns2() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "jan");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testMonthOnlyDecReturns12() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "nope", "Dec");
        act.then().body(equalTo("12"));
    }

    @Test(timeout = 60000)
    public void testNeitherMatchReturns0() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "xyz", "unknown");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUppercaseWedMarReturns4() {
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zebra", "yak", "x-ray", "wolf").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "WED", "MAR");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testThursdayThurAndAugReturns9() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "1", "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "thur", "Aug");
        act.then().body(equalTo("9"));
    }
}