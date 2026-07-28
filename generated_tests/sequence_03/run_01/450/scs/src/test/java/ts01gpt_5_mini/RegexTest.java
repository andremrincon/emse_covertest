package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternReturns200() throws Exception {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(ping, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "http://abc/xyz";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get("/api/pat/" + enc);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternReturns200() throws Exception {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(ping, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "mon05jan";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get("/api/pat/" + enc);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternReturns200() throws Exception {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(ping, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "12.3e+45";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get("/api/pat/" + enc);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternReturns200() throws Exception {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(ping, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "unmatched-string-XYZ";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get("/api/pat/" + enc);
        resp.then().statusCode(200);
    }
}