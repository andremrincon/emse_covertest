package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testPat_ShortPattern_returnsZero() {
        String txt = "sometext";
        String pat = "a";
        given().pathParam("txt", "healthcheck").when().get(base + "/api/pat/{txt}").then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", txt).pathParam("pat", pat).when().get(base + "/api/pat/{txt}/{pat}");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_PatternFound_returnsOne() {
        String txt = "ABABCABAB";
        String pat = "ABAB";
        given().pathParam("txt", "healthcheck").when().get(base + "/api/pat/{txt}").then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", txt).pathParam("pat", pat).when().get(base + "/api/pat/{txt}/{pat}");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_ReverseFound_returnsTwo() {
        String txt = "zzcbaqq";
        String pat = "abc";
        given().pathParam("txt", "healthcheck").when().get(base + "/api/pat/{txt}").then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", txt).pathParam("pat", pat).when().get(base + "/api/pat/{txt}/{pat}");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_PatThenReverseAdjacent_returnsIndexZero() {
        String pat = "abc";
        String txt = "abccba";
        given().pathParam("txt", "healthcheck").when().get(base + "/api/pat/{txt}").then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", txt).pathParam("pat", pat).when().get(base + "/api/pat/{txt}/{pat}");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_ReverseThenPatAdjacent_returnsIndexZero() {
        String pat = "abc";
        String txt = "cbaabc";
        given().pathParam("txt", "healthcheck").when().get(base + "/api/pat/{txt}").then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", txt).pathParam("pat", pat).when().get(base + "/api/pat/{txt}/{pat}");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_PatAndReverseNonAdjacent_returnsIndexOfFirst() {
        String pat = "abc";
        String txt = "abcxxxcba";
        given().pathParam("txt", "healthcheck").when().get(base + "/api/pat/{txt}").then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", txt).pathParam("pat", pat).when().get(base + "/api/pat/{txt}/{pat}");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_TxtOnlyEndpoint_available_status200() {
        String txt = "TheQuickBrownFox";
        given().pathParam("txt", "healthcheck").when().get(base + "/api/pat/{txt}").then().statusCode(lessThan(300));
        Response act = given().pathParam("txt", txt).when().get(base + "/api/pat/{txt}");
        assertEquals(200, act.getStatusCode());
    }
}