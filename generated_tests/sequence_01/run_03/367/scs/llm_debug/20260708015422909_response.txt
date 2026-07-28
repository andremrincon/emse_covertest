package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnZeroWhenIIsMinus4AndSIsAbab() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        act.then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testResultSixWhenIIsFiveAndSIsAbab() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        act.then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testFinalTenWhenIIsMinus4AndSIsZzzz() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, "zzzz");
        act.then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testSEqualsBranchIsExercisedWithBaab() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 7, "baab");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testResultSixWhenIIsSixHundredAndSIsAbab() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 600, "abab");
        act.then().statusCode(200).assertThat().body(equalTo("10"));
    }
}