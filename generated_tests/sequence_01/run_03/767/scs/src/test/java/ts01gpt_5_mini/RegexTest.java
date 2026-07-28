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
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlInputProducesOkStatus() throws Exception {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + setupId).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://abc/def", StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/" + txt);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testDateInputProducesOkStatus() throws Exception {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + setupId).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("mon12jan", StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/" + txt);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testFloatingPointExponentialProducesOkStatus() throws Exception {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + setupId).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("1.2e+34", StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/" + txt);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testNonePatternProducesOkStatus() throws Exception {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + setupId).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("hello_world-123", StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/" + txt);
        assertEquals(200, act.statusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpointWithPatternProducesOkStatus() throws Exception {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + setupId).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("ABABCABAB", StandardCharsets.UTF_8.name());
        String pat = URLEncoder.encode("ABAB", StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/" + txt + "/" + pat);
        assertEquals(200, act.statusCode());
    }
}