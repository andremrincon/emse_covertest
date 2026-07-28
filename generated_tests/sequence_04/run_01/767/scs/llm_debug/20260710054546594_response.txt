package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testPat_PatLenTwo_returnsOk() {
        String setup = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = "nooccurrencehere";
        String pat = "ab";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_PatFoundOnly_returnsOk() {
        String setup = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = "xxabcxx";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_ReverseFoundOnly_returnsOk() {
        String setup = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = "xxcba00";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_BothNonAdjacent_returnsOk() {
        String setup = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = "abcxxxxcba";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_Palindrome_PatThenRevAdjacent_returnsOk() {
        String setup = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = "xxabccbaYY";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_Palindrome_RevThenPatAdjacent_returnsOk() {
        String setup = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", setup).then().statusCode(lessThan(300));
        String txt = "zzcbaabcp";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }
}