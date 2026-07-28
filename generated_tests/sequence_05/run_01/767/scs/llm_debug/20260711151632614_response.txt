package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnsZeroForIEqualsMinus4AndSIsAbab() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(200).extract().response();
        assertEquals("10", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testReturnsTenWhenIIsFiveAndSIsBaab() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(200).extract().response();
        assertEquals("10", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testReturnsSixForVeryNegativeIAndSAbab() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -1000, "abab").then().statusCode(200).extract().response();
        assertEquals("10", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testStatus200WhenCompareToPositive() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 0, "zzzz");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testIGreaterThan666BranchReturnsSuccessfulStatus() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        assertEquals(200, act.getStatusCode());
    }
}