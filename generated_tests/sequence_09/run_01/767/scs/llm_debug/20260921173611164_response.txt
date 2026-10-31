package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        BASE = cfg;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlBranch() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", URLEncoder.encode(arrangeId, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectDateBranch() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", URLEncoder.encode(arrangeId, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "wed12aug";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeBranch() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", URLEncoder.encode(arrangeId, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "1.2e+34";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneBranch() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/pat/{txt}", URLEncoder.encode(arrangeId, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "foobar";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response act = given().baseUri(BASE).when().get("/api/pat/{txt}", enc);
        act.then().statusCode(200);
    }
}