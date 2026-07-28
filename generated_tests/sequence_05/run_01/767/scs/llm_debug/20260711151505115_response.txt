package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("api.baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("API_BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    private static String enc(String v) {
        try {
            return URLEncoder.encode(v, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testUrlInputReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", uuid).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String encoded = enc(txt);
        Response r = given().when().get("/api/pat/{txt}", encoded);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateInputReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "a", "b", uuid).then().statusCode(lessThan(300));
        String txt = "mon01jan";
        Response r = given().when().get("/api/pat/{txt}", txt);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpeInputReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "x", "y", uuid).then().statusCode(lessThan(300));
        String txt = "12.34e+05";
        Response r = given().when().get("/api/pat/{txt}", txt);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInputReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "one", "two", uuid).then().statusCode(lessThan(300));
        String txt = "hello";
        Response r = given().when().get("/api/pat/{txt}", txt);
        r.then().statusCode(200);
    }
}