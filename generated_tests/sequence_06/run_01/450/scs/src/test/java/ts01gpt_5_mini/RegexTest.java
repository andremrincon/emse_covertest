package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesUrlReturns200() {
        given().when().get("/api/pat/seed").then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String path = "/api/pat/" + enc(txt);
        Response resp = given().when().get(path);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesDateReturns200() {
        given().when().get("/api/pat/seedDate").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String path = "/api/pat/" + enc(txt);
        Response resp = given().when().get(path);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesFpeReturns200() {
        given().when().get("/api/pat/seedFpe").then().statusCode(lessThan(300));
        String txt = "12.34e+56";
        String path = "/api/pat/" + enc(txt);
        Response resp = given().when().get(path);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesNoneReturns200() {
        given().when().get("/api/pat/seedNone").then().statusCode(lessThan(300));
        String txt = "randomText123";
        String path = "/api/pat/" + enc(txt);
        Response resp = given().when().get(path);
        assertEquals(200, resp.getStatusCode());
    }
}