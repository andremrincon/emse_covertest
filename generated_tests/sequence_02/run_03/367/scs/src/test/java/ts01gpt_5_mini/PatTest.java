package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatternNotPresentReturnsZero() {
        String arrangeTxt = "health-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "abcdefg";
        String pat = "hij";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternPresentOnlyReturnsOne() {
        String arrangeTxt = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "xxhelloYY";
        String pat = "hello";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReversePatternPresentOnlyReturnsTwo() {
        String arrangeTxt = "check-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "xxollehzz";
        String pat = "hello";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testBothPatternAndReverseNonAdjacentReturnsIndex() {
        String arrangeTxt = "arr-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "foohelloBARollehend";
        String pat = "hello";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testAdjacentPalindromePatThenReverseReturnsIndex() {
        String arrangeTxt = "arr2-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "starthelloollehend";
        String pat = "hello";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testShortPatternIgnoredReturnsZero() {
        String arrangeTxt = "arr3-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "ababa";
        String pat = "ab";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }
}