package ts01gpt_5_mini;

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
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testUrlPatternTriggersUrlBranch() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "http%3A%2F%2Fabc%2Fdef");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternTriggersDateBranch() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "mon12jan");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternTriggersFpeBranch() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "12.34e%2B56");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternTriggersNoneBranch() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeId).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "hello");
        act.then().statusCode(200);
    }
}