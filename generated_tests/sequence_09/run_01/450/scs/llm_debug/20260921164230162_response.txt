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
    public void test_pat_only_returns_1() {
        String arrangeTxt = "healthcheck-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "xxABCyy";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_only_returns_2() {
        String arrangeTxt = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "yyCBAzz";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_nonadjacent_returns_index() {
        String arrangeTxt = "prep-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "ABCxxCBA";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_then_reverse_adjacent_returns_index() {
        String arrangeTxt = "chk-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "ABCCBA";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_nonadjacent_returns_index() {
        String arrangeTxt = "ready-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "CBAxxABC";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_short_pat_length_returns_0() {
        String arrangeTxt = "alive-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "anytextcontainingab";
        String pat = "ab";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }
}