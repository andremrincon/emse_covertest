package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {
    private static final String BASE;
    static {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        BASE = b;
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testNoMatchReturnsZero() {
        String txt = "abcdefghijklmnopqrstuvwxyz";
        String pat = "XYZ";
        given().when().get(BASE + "/api/pat/" + enc("healthcheck")).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        assertEquals("0", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testOnlyPatFoundReturnsOne() {
        String txt = "xxxABCyyy";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("info")).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        assertEquals("1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testOnlyReverseFoundReturnsTwo() {
        String txt = "qqCBAzz";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("ping")).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        assertEquals("2", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testPatThenReverseNonContiguousReturnsIndex() {
        String txt = "xABCxxCBAy";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("setup-" + pat)).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        assertEquals("1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testReverseThenPatContiguousReturnsIndex() {
        String txt = "zCBAABCq";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("ready-" + pat)).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        assertEquals("1", resp.getBody().asString().trim());
    }
}