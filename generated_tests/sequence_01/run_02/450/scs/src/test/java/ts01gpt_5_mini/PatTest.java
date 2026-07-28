package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatPresentReturnsOne() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "xxxABCyyy", "ABC");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReversePresentReturnsTwo() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "zzzCBAaaa", "ABC");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABCxxxCBA", "ABC");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseAdjacentPalindromeReturnsIndex() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABCCBA", "ABC");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testShortPatternLengthTwoReturnsZero() {
        given().when().get("/api/pat/{txt}", "startup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABAB", "AB");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoMatchReturnsZero() {
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "xxxxxxxx", "ABC");
        act.then().body(equalTo("0"));
    }
}