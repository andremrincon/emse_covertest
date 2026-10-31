package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_PatTxtEndpoint_Returns200() {
        String txt = "The quick brown fox jumps over the lazy dog.";
        String txtEncoded;
        try {
            txtEncoded = URLEncoder.encode(txt, "UTF-8").replace("+", "%20");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        given().when().get("/api/pat/{txt}", txtEncoded).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", txtEncoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_PatTooShort_PatLengthOne_Returns200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String txt = "some-text-" + uuid;
        String pat = "a";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_PatFound_Returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_ReverseFoundOnly_Returns200() {
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        String txt = "xxedcbaYY";
        String pat = "abcde";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_Palindrome_PatFollowedByReverse_Immediate_Returns200() {
        given().when().get("/api/pat/{txt}", "probe2").then().statusCode(lessThan(300));
        String txt = "ABAABA";
        String pat = "ABA";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_Palindrome_ReverseFollowedByPat_Immediate_Returns200() {
        given().when().get("/api/pat/{txt}", "probe3").then().statusCode(lessThan(300));
        String txt = "CBAABC";
        String pat = "ABC";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }
}