package ts01gpt_5_mini;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPattern() throws Exception {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.toString());
        given().when().get("/api/pat/{txt}", enc).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePattern() throws Exception {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.toString());
        given().when().get("/api/pat/{txt}", enc).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePattern() throws Exception {
        given().when().get("/api/costfuns/{i}/{s}", "1", "algorithm").then().statusCode(lessThan(300));
        String txt = "12.3e+45";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.toString());
        given().when().get("/api/pat/{txt}", enc).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePattern() throws Exception {
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        String txt = "NoMatch123";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.toString());
        given().when().get("/api/pat/{txt}", enc).then().statusCode(200);
    }
}