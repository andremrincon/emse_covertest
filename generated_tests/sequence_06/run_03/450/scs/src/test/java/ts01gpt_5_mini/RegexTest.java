package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RegexTest {

    @BeforeClass
    public static void setupBase() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        if (base.endsWith("/")) base = base.substring(0, base.length() - 1);
        RestAssured.baseURI = base;
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testUrlPatternReturns200() {
        given().when().get("/api/pat/{txt}", enc("The quick brown")).then().statusCode(lessThan(300));
        String unique = "http://abc_" + UUID.randomUUID().toString().substring(0, 8) + "/def";
        String encoded = enc(unique);
        Response r = given().when().get("/api/pat/{txt}", encoded);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternReturns200() {
        given().when().get("/api/pat/{txt}", enc("example")).then().statusCode(lessThan(300));
        String dateTxt = "mon01jan" + UUID.randomUUID().toString().substring(0, 4);
        String candidate = dateTxt.substring(0, 9);
        String encoded = enc(candidate);
        Response r = given().when().get("/api/pat/{txt}", encoded);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternReturns200() {
        given().when().get("/api/pat/{txt}", enc("setup")).then().statusCode(lessThan(300));
        String fpe = "1.2e+03";
        String unique = fpe + UUID.randomUUID().toString().substring(0, 2);
        String encoded = enc(unique.substring(0, 6));
        Response r = given().when().get("/api/pat/{txt}", enc("1.2e+03"));
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternReturns200() {
        given().when().get("/api/pat/{txt}", enc("pre")).then().statusCode(lessThan(300));
        String none = "no-match-" + UUID.randomUUID().toString().substring(0, 8);
        Response r = given().when().get("/api/pat/{txt}", enc(none));
        r.then().statusCode(200);
    }
}