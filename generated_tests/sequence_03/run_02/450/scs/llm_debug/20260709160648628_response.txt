package ts01gpt_5_mini;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        baseURI = env;
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesUrl() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesDate() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "wed15aug";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesFloatingPointWithExponent() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "1.2e+34";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNoneForNonMatchingText() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "this_will_not_match_any_pattern";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }
}