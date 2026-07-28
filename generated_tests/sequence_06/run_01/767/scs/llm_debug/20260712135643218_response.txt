package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private static String base;

    @BeforeClass
    public static void init() throws Exception {
        base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String enc(String s) throws Exception {
        return URLEncoder.encode(s, "UTF-8");
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() throws Exception {
        String txt = "hello world";
        String pat = "ab";
        String setupTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + enc(setupTxt)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternFoundReturnsOne() throws Exception {
        String txt = "xxxabcyyy";
        String pat = "abc";
        String setupTxt = "health-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + enc(setupTxt)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundReturnsTwo() throws Exception {
        String txt = "xxxcbaaa";
        String pat = "abc";
        String setupTxt = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + enc(setupTxt)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testBothNonAdjacentReturnsIndex() throws Exception {
        String pat = "abc";
        String txt = "abcZZZcba";
        String setupTxt = "check-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + enc(setupTxt)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testAdjacentPatThenReverseReturnsIndex() throws Exception {
        String pat = "abc";
        String txt = "abccba";
        String setupTxt = "prep-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + enc(setupTxt)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testAdjacentReverseThenPatReturnsIndex() throws Exception {
        String pat = "abc";
        String txt = "cbaabc";
        String setupTxt = "prep2-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/" + enc(setupTxt)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().body(equalTo("0"));
    }
}