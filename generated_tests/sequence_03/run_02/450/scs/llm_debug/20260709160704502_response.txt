package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatTooShortReturnsZero() {
        given().when().get("/api/pat/{txt}", "sampletext").then().statusCode(lessThan(300));
        String txt = "irrelevant";
        String pat = "ab";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        String body = act.getBody().asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testPatFoundReturnsOne() {
        given().when().get("/api/pat/{txt}", "dummy").then().statusCode(lessThan(300));
        String txt = "xxabcxx";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        String body = act.getBody().asString();
        assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testReverseFoundReturnsTwo() {
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        String txt = "xxcba00";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        String body = act.getBody().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testPatThenReverseNonAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        String txt = "xxabcyycba";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        String body = act.getBody().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testPatThenReverseAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "prep").then().statusCode(lessThan(300));
        String txt = "xxabccba";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        String body = act.getBody().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testReverseThenPatNonAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        String txt = "xxcbaYYabc";
        String pat = "abc";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        String body = act.getBody().asString();
        assertEquals("2", body);
    }
}