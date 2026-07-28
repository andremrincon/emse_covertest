package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv().containsKey("BASE_URL") ? System.getenv("BASE_URL") : System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_shortPattern_returns0() {
        String uid = UUID.randomUUID().toString();
        String txt = "someText" + uid;
        String pat = "ab";
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_onlyPattern_returns1() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABC";
        String txt = "xxx" + pat + "yyy" + uid;
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "2", "3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_onlyReverse_returns2() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABC";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "prefix" + patrev + "suffix" + uid;
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "4", "5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_both_noncontiguous_returnsIndexOfFirstOccurrence() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABC";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = pat + "xxx" + patrev + uid;
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "6", "7").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_reverseThenPat_noncontiguous_returnsIndexOfReverse() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABC";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = patrev + "middle" + pat + uid;
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "8", "9").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_txt_endpoint_returns200() {
        String uid = UUID.randomUUID().toString();
        String txt = "The quick brown fox " + uid;
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "10", "11").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }
}