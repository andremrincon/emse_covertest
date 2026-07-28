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

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsOk() throws Exception {
        String warm = "warmup-" + UUID.randomUUID();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(warm, StandardCharsets.UTF_8.name()))
                .then().statusCode(lessThan(300));
        String txt = "someexampletext";
        String pat = "ab";
        Response act = given().when().get("/api/pat/{txt}/{pat}",
                URLEncoder.encode(txt, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(pat, StandardCharsets.UTF_8.name()));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundWithoutReverseReturnsOk() throws Exception {
        String warm = "warmup-" + UUID.randomUUID();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(warm, StandardCharsets.UTF_8.name()))
                .then().statusCode(lessThan(300));
        String txt = "xxabcyyy";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}",
                URLEncoder.encode(txt, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(pat, StandardCharsets.UTF_8.name()));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnlyReturnsOk() throws Exception {
        String warm = "warmup-" + UUID.randomUUID();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(warm, StandardCharsets.UTF_8.name()))
                .then().statusCode(lessThan(300));
        String txt = "xxcbaYY";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}",
                URLEncoder.encode(txt, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(pat, StandardCharsets.UTF_8.name()));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternThenReverseNonContiguousReturnsOk() throws Exception {
        String warm = "warmup-" + UUID.randomUUID();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(warm, StandardCharsets.UTF_8.name()))
                .then().statusCode(lessThan(300));
        String txt = "abcxxxcba";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}",
                URLEncoder.encode(txt, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(pat, StandardCharsets.UTF_8.name()));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromicPatternPatThenReverseContiguousReturnsOk() throws Exception {
        String warm = "warmup-" + UUID.randomUUID();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(warm, StandardCharsets.UTF_8.name()))
                .then().statusCode(lessThan(300));
        String txt = "abccba";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}",
                URLEncoder.encode(txt, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(pat, StandardCharsets.UTF_8.name()));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseThenPatContiguousReturnsOk() throws Exception {
        String warm = "warmup-" + UUID.randomUUID();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(warm, StandardCharsets.UTF_8.name()))
                .then().statusCode(lessThan(300));
        String txt = "cbaabc";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}",
                URLEncoder.encode(txt, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(pat, StandardCharsets.UTF_8.name()));
        act.then().statusCode(200);
    }
}