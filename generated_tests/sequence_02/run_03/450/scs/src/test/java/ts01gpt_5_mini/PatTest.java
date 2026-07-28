package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void test_PatTooShort_returns0() {
        given().when().get("/api/pat/{txt}", "arrange-short").then().statusCode(lessThan(300));
        String txt = "someText";
        String pat = "ab";
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_PatFoundOnly_returns1() {
        given().when().get("/api/pat/{txt}", "arrange-pat1").then().statusCode(lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_ReverseFoundOnly_returns2() {
        given().when().get("/api/pat/{txt}", "arrange-rev1").then().statusCode(lessThan(300));
        String txt = "xxCBAyy";
        String pat = "ABC";
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_BothFound_patThenReverse_returnsIndex() {
        given().when().get("/api/pat/{txt}", "arrange-both1").then().statusCode(lessThan(300));
        String txt = "ABABBABA";
        String pat = "ABAB";
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_BothFound_reverseThenPatContiguous_returnsIndex() {
        given().when().get("/api/pat/{txt}", "arrange-both2").then().statusCode(lessThan(300));
        String txt = "CBAABC";
        String pat = "ABC";
        Response resp = given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}");
        assertEquals("0", resp.getBody().asString());
    }
}