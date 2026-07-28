package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatLengthTwoReturnsZero() {
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "irrelevant";
        String pat = "ab";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReturnsOne() {
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "xxxABCyyy";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundReturnsTwo() {
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "yyyCBAzzz";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testAdjacentPatThenRevReturnsIndex() {
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "ABCCBA";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testAdjacentRevThenPatReturnsIndex() {
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "CBAABC";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNonAdjacentPatThenRevReturnsIndex() {
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "ABCxCBA";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNonAdjacentRevThenPatReturnsIndex() {
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "CBAxABC";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().body(equalTo("0"));
    }
}