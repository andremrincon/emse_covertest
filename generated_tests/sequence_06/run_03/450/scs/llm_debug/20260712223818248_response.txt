package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {
    private static String BASE;

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        BASE = base;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testPatLengthTwo_returns200() {
        String txt = "abc";
        String pat = "ab";
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatOnly_returns200() {
        String txt = "xxABCyyy";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/{txt}", "sample").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testReverseOnly_returns200() {
        String txt = "zzzCBAqqq";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBothNonAdjacent_returns200() {
        String txt = "xxABCyyCBAzz";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBothAdjacent_patThenReverse_returns200() {
        String txt = "xxABCCBAyy";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacent_returns200() {
        String txt = "xxCBAABCyy";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/{txt}", "init").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, resp.getStatusCode());
    }
}