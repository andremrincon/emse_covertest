package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import java.net.URLEncoder;
import java.util.UUID;

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
    public void testSubjectMatchesUrlReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString().replace("-", "");
        String txt = "http://abc" + unique + "/def" + unique;
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = when().get("/api/pat/" + enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDateReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "A", "B", "C").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = when().get("/api/pat/" + enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpeReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "one", "two", "three").then().statusCode(lessThan(300));
        String txt = "12.3e+45";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = when().get("/api/pat/" + enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesNoneReturns200() throws Exception {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "alpha", "beta", "gamma").then().statusCode(lessThan(300));
        String txt = "hello_world_" + UUID.randomUUID().toString().substring(0,8);
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = when().get("/api/pat/" + enc);
        act.then().statusCode(200);
    }
}