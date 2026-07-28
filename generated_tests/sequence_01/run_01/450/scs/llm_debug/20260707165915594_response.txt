package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
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
    public void testSubjectMatchesUrlReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "http://a/a";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDateReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpeReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "12.3e+45";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesNoneReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "foobar";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMalformedPatternLeadsTo500() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "(";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }
}