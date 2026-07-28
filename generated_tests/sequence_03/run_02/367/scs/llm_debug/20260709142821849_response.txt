package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlClassification() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String input = "http://a/b";
        String enc = URLEncoder.encode(input, StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", enc);
        assertEquals("none", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDateClassification() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String input = "wed12aug";
        String enc = URLEncoder.encode(input, StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", enc);
        assertEquals("date", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFpeClassification() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String input = "12.3e+45";
        String enc = URLEncoder.encode(input, StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", enc);
        assertEquals("none", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoneClassification() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String input = "hello";
        String enc = URLEncoder.encode(input, StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", enc);
        assertEquals("none", resp.getBody().asString());
    }
}