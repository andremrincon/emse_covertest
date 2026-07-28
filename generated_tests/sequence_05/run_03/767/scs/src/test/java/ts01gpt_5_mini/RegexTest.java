package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testUrlPattern() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        String encoded;
        try {
            encoded = URLEncoder.encode("http://user/site", StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Response resp = given().when().get("/api/pat/{txt}", encoded);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDatePattern() {
        given().when().get("/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "mon05jan");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFpePattern() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        String encoded;
        try {
            encoded = URLEncoder.encode("12.34e+05", StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Response resp = given().when().get("/api/pat/{txt}", encoded);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNonePattern() {
        given().when().get("/api/pat/{txt}", "pre").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "xyz");
        assertEquals(200, resp.getStatusCode());
    }
}