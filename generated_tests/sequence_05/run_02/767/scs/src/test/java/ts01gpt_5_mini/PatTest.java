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
    public void test_shortPattern_returnsZero_status200() {
        String txt = "The quick brown fox";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, "ab");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_then_reverse_nonAdjacent_returnsIndex_bodyCheck() {
        String txt = "ABCxxCBA";
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, "ABC");
        assertEquals("0", r.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void test_reverseFoundFirst_then_pat_returnsIndex_bodyCheck() {
        String txt = "CBAxxABC";
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, "ABC");
        assertEquals("0", r.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void test_palindrome_contiguous_returnsIndex_bodyCheck() {
        String txt = "ABCCBArest";
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, "ABC");
        assertEquals("0", r.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void test_pat_notPresent_returnsZero_bodyCheck() {
        String txt = "HELLOWORLD";
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, "XYZ");
        assertEquals("0", r.getBody().asString().trim());
    }
}