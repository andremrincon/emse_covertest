package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private final String base = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testPat_shortPattern_returns0() {
        given().when().get(base + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        String txt = "hello";
        String pat = "ab";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPat_onlyPatFound_returns1() {
        given().when().get(base + "/api/pat/{txt}", "init").then().statusCode(lessThan(300));
        String uid = UUID.randomUUID().toString();
        String txt = "xyz" + uid + "abcdef";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPat_onlyReverseFound_returns2() {
        given().when().get(base + "/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        String uid = UUID.randomUUID().toString();
        String txt = "zzz" + uid + "cbaqq";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPat_patThenReverse_nonAdjacent_returnsIndex() {
        given().when().get(base + "/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        String txt = "abcxxxcba";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPat_patThenReverse_adjacent_returnsIndex() {
        given().when().get(base + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        String txt = "abccba";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPat_reverseThenPat_adjacent_returnsIndex() {
        given().when().get(base + "/api/pat/{txt}", "start").then().statusCode(lessThan(300));
        String txt = "cbaabc";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPat_noMatches_patlenGreaterThan2_returns0() {
        given().when().get(base + "/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        String txt = "xxxxxxxxxxxx";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("0"));
    }
}