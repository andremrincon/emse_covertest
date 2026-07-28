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
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_shortPattern_returnsZero() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String txt = "any text";
        String pat = "ab";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void test_patFollowedByReverse_adjacent_returnsIndex() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String pat = "ABC";
        String patrev = "CBA";
        String txt = pat + patrev;
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void test_reverseFollowedByPat_adjacent_returnsIndex() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String pat = "ABC";
        String patrev = "CBA";
        String txt = patrev + pat;
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void test_patThenReverse_nonAdjacent_returnsIndex() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String pat = "ABC";
        String patrev = "CBA";
        String txt = "ABCx" + patrev;
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void test_onlyReversePresent_returnsTwo() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String pat = "ABC";
        String patrev = "CBA";
        String txt = "xx" + patrev + "yy";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void test_noMatch_returnsZero_forLongPat() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        String pat = "PATTERN";
        String txt = "the quick brown fox jumps over the lazy dog";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", act.asString());
    }

}