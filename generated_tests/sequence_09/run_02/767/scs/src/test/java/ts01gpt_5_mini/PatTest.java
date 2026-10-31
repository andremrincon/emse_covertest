package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        BASE = env;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void test_PatShortPatternReturnsZero() {
        String setupTxt = "health-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get(BASE + "/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "some-text";
        String pat = "ab";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get(BASE + "/api/pat/{txt}/{pat}").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_PatOnlyFoundReturnsOne() {
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get(BASE + "/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "abcxxxx";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get(BASE + "/api/pat/{txt}/{pat}").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_PatReverseOnlyReturnsTwo() {
        String setupTxt = "ping-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get(BASE + "/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "xxxcba";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get(BASE + "/api/pat/{txt}/{pat}").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_PatBothNonAdjacentReturnsIndexFive() {
        String setupTxt = "init-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get(BASE + "/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "xxxxxabcxxxcba";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get(BASE + "/api/pat/{txt}/{pat}").then().body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void test_PatBothAdjacentReturnsIndexFour() {
        String setupTxt = "warm-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get(BASE + "/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "xxxxabccba";
        String pat = "abc";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get(BASE + "/api/pat/{txt}/{pat}").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void test_PatEndpointProcessesLongText() {
        String setupTxt = "probe-" + UUID.randomUUID().toString();
        given().pathParam("txt", setupTxt).when().get(BASE + "/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "The quick brown fox jumps over the lazy dog.";
        given().pathParam("txt", txt).when().get(BASE + "/api/pat/{txt}").then().statusCode(lessThan(300));
    }
}