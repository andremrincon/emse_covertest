package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void datePattern_txtMatchesDate_returns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "a" + unique).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "wed12jan");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fpePattern_txtMatchesFpe_returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "15.5e+02");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void noPattern_txtMatchesNone_returns200() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "hello");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void patEndpoint_withTextAndPattern_returns200() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        act.then().statusCode(200);
    }
}