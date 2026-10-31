package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatTxtEndpoint_returns200() {
        given().when().get("/api/pat/{txt}", "seed").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testShortPattern_returns0() {
        given().when().get("/api/pat/{txt}", "seed").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "banana", "ab");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPatternFound_returns1() {
        given().when().get("/api/pat/{txt}", "seed").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "xxABCyy", "ABC");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testReverseFound_returns2() {
        given().when().get("/api/pat/{txt}", "seed").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "xxCBAyy", "ABC");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testBothFound_returnsIndexOfFirstOccurrence() {
        given().when().get("/api/pat/{txt}", "seed").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "aaCBAaaABC", "ABC");
        assertEquals("2", act.asString());
    }
}