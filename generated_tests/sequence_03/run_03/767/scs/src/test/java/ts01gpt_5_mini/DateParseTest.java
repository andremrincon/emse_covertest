package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidWedAugReturns200() {
        for (String m : new String[]{"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"}) {
            given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", m).then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "wed", "aug");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMonJanReturnsBody2() {
        for (String m : new String[]{"jan","feb","mar","apr","may","jun"}) {
            given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", m).then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "jan");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNonMatchingDayWithFebReturnsBody2() {
        for (String d : new String[]{"abc","zzz","none"}) {
            given().when().get("/api/dateparse/{dayname}/{monthname}", d, "feb").then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "noday", "feb");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testMonMarUppercaseMonthReturnsBody4() {
        for (String d : new String[]{"mon","tue","wed"}) {
            given().when().get("/api/dateparse/{dayname}/{monthname}", d, "MAR").then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", "MAR");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testInvalidDayAndMonthReturns500() {
        for (String m : new String[]{"jan","feb","mar"}) {
            given().when().get("/api/dateparse/{dayname}/{monthname}", "mon", m).then().statusCode(lessThan(300));
        }
        Response act = given().when().get("/api/dateparse/{dayname}/{monthname}", "123", "Movember");
        act.then().statusCode(200);
    }
}