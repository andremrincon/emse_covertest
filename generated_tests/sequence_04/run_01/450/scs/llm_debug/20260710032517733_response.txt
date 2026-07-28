package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternMatches_returns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        given().when().get("/api/pat/{txt}", encoded).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternMatches_returns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternMatches_returns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "12.3e+45";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        given().when().get("/api/pat/{txt}", encoded).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePattern_returns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "hello_world";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatWithPatternEndpoint_returns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB").then().statusCode(200);
    }
}