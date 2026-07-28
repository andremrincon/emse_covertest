package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void setup() {
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
    public void testPatEndpointRecognizesUrlPattern() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/" + encoded);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesDatePattern() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/" + encoded);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesFloatingWithExponentPattern() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String txt = "1.2e+34";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/" + encoded);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpointReturnsNoneForNonMatchingText() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        String txt = "helloWorld123";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(RestAssured.baseURI + "/api/pat/" + encoded);
        assertEquals(200, resp.getStatusCode());
    }
}