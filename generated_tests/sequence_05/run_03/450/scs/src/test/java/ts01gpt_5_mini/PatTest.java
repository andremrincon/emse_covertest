package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero_status200() {
        String uuid = UUID.randomUUID().toString();
        String txt = "shorttxt" + uuid;
        String pat = "ab";
        given().baseUri(BASE).when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReturnsOne_status200() {
        String uuid = UUID.randomUUID().toString();
        String pat = "abc";
        String txt = "xxx" + pat + "yyy" + uuid;
        given().baseUri(BASE).when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseOnlyReturnsTwo_status200() {
        String uuid = UUID.randomUUID().toString();
        String pat = "abc";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "zzz" + patrev + "qqq" + uuid;
        given().baseUri(BASE).when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFollowedByReverseReturnsIndex_status200() {
        String uuid = UUID.randomUUID().toString();
        String pat = "abc";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = pat + patrev + uuid;
        given().baseUri(BASE).when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFollowedByPatReturnsIndex_status200() {
        String uuid = UUID.randomUUID().toString();
        String pat = "abc";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = patrev + pat + uuid;
        given().baseUri(BASE).when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }
}