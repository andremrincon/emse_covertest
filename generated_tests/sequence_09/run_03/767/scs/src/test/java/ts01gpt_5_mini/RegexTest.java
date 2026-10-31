package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternReturns200() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        String arrangePath = "/api/pat/" + URLEncoder.encode(arrangeId, "UTF-8");
        RestAssured.given().when().get(arrangePath).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String path = "/api/pat/" + URLEncoder.encode(txt, "UTF-8");
        Response act = RestAssured.given().when().get(path);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternReturns200() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        String arrangePath = "/api/pat/" + URLEncoder.encode(arrangeId, "UTF-8");
        RestAssured.given().when().get(arrangePath).then().statusCode(lessThan(300));
        String txt = "wed05mar";
        String path = "/api/pat/" + URLEncoder.encode(txt, "UTF-8");
        Response act = RestAssured.given().when().get(path);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingPointExponentPatternReturns200() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        String arrangePath = "/api/pat/" + URLEncoder.encode(arrangeId, "UTF-8");
        RestAssured.given().when().get(arrangePath).then().statusCode(lessThan(300));
        String txt = "12.3e+45";
        String path = "/api/pat/" + URLEncoder.encode(txt, "UTF-8");
        Response act = RestAssured.given().when().get(path);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneFallbackReturns200() throws Exception {
        String arrangeId = UUID.randomUUID().toString();
        String arrangePath = "/api/pat/" + URLEncoder.encode(arrangeId, "UTF-8");
        RestAssured.given().when().get(arrangePath).then().statusCode(lessThan(300));
        String txt = "xyz";
        String path = "/api/pat/" + URLEncoder.encode(txt, "UTF-8");
        Response act = RestAssured.given().when().get(path);
        act.then().statusCode(200);
    }
}