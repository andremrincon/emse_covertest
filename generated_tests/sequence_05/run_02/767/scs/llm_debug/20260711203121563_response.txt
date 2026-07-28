package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private static String enc(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testPatRecognizesUrlPattern() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        String txt = enc("http://a/b");
        Response act = given().when().get("/api/pat/" + txt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRecognizesDatePattern() {
        given().when().get("/api/pat/{txt}", "sample").then().statusCode(lessThan(300));
        String txt = enc("mon01jan");
        Response act = given().when().get("/api/pat/" + txt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRecognizesFloatingPointExponentialPattern() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        String txt = enc("12.34e+05");
        Response act = given().when().get("/api/pat/" + txt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRecognizesNonePattern() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        String txt = enc("this_does_not_match_any_pattern");
        Response act = given().when().get("/api/pat/" + txt);
        act.then().statusCode(200);
    }
}