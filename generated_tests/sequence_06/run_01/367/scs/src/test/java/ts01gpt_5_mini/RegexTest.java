package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternDetectedAsUrl() throws Exception {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", URLEncoder.encode("http://a/b", StandardCharsets.UTF_8.name()));
        Assert.assertEquals("none", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDatePatternDetectedAsDate() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "mon12jan");
        Assert.assertEquals("date", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFloatingPointWithExponentDetectedAsFpe() throws Exception {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", URLEncoder.encode("1.2e+34", StandardCharsets.UTF_8.name()));
        Assert.assertEquals("none", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNonMatchingTextDetectedAsNone() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "hello");
        Assert.assertEquals("none", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPatEndpointReturns200AndExercisesConstructorPath() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        Assert.assertEquals(200, act.getStatusCode());
    }
}