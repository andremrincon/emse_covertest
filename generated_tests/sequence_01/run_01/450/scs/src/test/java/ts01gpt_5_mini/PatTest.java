package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
    public void testShortPatternReturnsZero() {
        String txt = "sample-" + UUID.randomUUID().toString();
        String pat = "ab";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternFoundReturnsOne() {
        String txt = "xxabcyy-" + UUID.randomUUID().toString();
        String pat = "abc";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundReturnsTwo() {
        String txt = "xxxcbayyy-" + UUID.randomUUID().toString();
        String pat = "abc";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacentReturnsIndex() {
        String txt = "ABABxxxBABA-" + UUID.randomUUID().toString();
        String pat = "ABAB";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseAdjacentReturnsIndexForPalindromeCase() {
        String txt = "ABABBABA-" + UUID.randomUUID().toString();
        String pat = "ABAB";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseThenPatNonAdjacentReturnsIndex() {
        String txt = "BABAxxxABAB-" + UUID.randomUUID().toString();
        String pat = "ABAB";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacentReturnsIndexForOppositePalindromeCase() {
        String txt = "BABAABAB-" + UUID.randomUUID().toString();
        String pat = "ABAB";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }
}