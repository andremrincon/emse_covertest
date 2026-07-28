package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl") != null ? System.getProperty("baseUrl") : (System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternReturns200() {
        String setupId = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "http://a/a");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testDatePatternReturns200() {
        String setupId = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "mon12jan");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingPointExponentPatternReturns200() {
        String setupId = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "12.3e+45");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternReturns200() {
        String setupId = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "xyz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatWithPatternEndpointReturns200() {
        String setupId = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "arr", setupId, "v").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "banana");
        resp.then().statusCode(200);
    }
}