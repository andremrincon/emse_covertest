package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesUrlPattern_returns200() {
        String arrangeTxt = "The quick brown fox jumps over the lazy dog.";
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", "http://john/doe").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDatePattern_returns200() {
        String arrangeTxt = "The quick brown fox jumps over the lazy dog.";
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", "wed12apr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFloatingPointExponent_returns200() {
        String arrangeTxt = "The quick brown fox jumps over the lazy dog.";
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", "1.0e+09").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoMatchReturnsNonePath_returns200() {
        String arrangeTxt = "The quick brown fox jumps over the lazy dog.";
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", "hello").then().statusCode(200);
    }
}