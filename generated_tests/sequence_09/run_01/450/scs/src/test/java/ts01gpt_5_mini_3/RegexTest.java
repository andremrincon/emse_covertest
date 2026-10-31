package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
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
    public void testUrlPatternReturns200() throws Exception {
        String rnd = URLEncoder.encode(UUID.randomUUID().toString(), StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://user/service", StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", txt);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testDatePatternReturns200() throws Exception {
        String rnd = URLEncoder.encode(UUID.randomUUID().toString(), StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "wed12apr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingPointWithExponentReturns200() throws Exception {
        String rnd = URLEncoder.encode(UUID.randomUUID().toString(), StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        String fpe = URLEncoder.encode("12.34e+05", StandardCharsets.UTF_8.name());
        Response resp = given().when().get("/api/pat/{txt}", fpe);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternReturns200() throws Exception {
        String rnd = URLEncoder.encode(UUID.randomUUID().toString(), StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "foobar");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointWithPatternReturns200() throws Exception {
        String rnd = URLEncoder.encode(UUID.randomUUID().toString(), StandardCharsets.UTF_8.name());
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        resp.then().statusCode(200);
    }
}