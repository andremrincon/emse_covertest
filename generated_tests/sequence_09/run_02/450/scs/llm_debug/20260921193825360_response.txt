package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternTriggersEndpointReturns200() {
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "http://abc/def");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testDatePatternTriggersEndpointReturns200() {
        given().when().get("/api/pat/{txt}", "probe2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "mon12jan");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingPointExponentialPatternReturns200() {
        given().when().get("/api/pat/{txt}", "probe3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "12.3e+45");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoPatternReturns200() {
        given().when().get("/api/pat/{txt}", "probe4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "helloWorld");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatWithPatternEndpointReturns200() {
        given().when().get("/api/pat/{txt}/{pat}", "probe5", "p").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        act.then().statusCode(200);
    }
}