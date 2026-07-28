package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlRecognition() throws Exception {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeToken, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String urlTxt = "http://abc/def";
        Response act = given().when().get("/api/pat/{txt}", URLEncoder.encode(urlTxt, StandardCharsets.UTF_8.name()));
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectDateRecognition() throws Exception {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeToken, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String dateTxt = "mon12jan";
        Response act = given().when().get("/api/pat/{txt}", URLEncoder.encode(dateTxt, StandardCharsets.UTF_8.name()));
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectFpeRecognition() throws Exception {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeToken, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String fpeTxt = "1.2e+34";
        Response act = given().when().get("/api/pat/{txt}", URLEncoder.encode(fpeTxt, StandardCharsets.UTF_8.name()));
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectNoneRecognition() throws Exception {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeToken, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String noneTxt = "none-" + UUID.randomUUID().toString();
        Response act = given().when().get("/api/pat/{txt}", URLEncoder.encode(noneTxt, StandardCharsets.UTF_8.name()));
        assertEquals(200, act.getStatusCode());
    }
}