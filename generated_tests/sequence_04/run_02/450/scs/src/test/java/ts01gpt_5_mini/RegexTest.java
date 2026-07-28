package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
    public void testUrlRecognition() throws Exception {
        String arrangeTxt = URLEncoder.encode("The quick brown fox", "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://abc/def", "UTF-8");
        Response act = given().when().get("/api/pat/{txt}", txt);
        act.then().statusCode(200).body(equalTo("none"));
    }

    @Test(timeout = 60000)
    public void testDateRecognition() throws Exception {
        String arrangeTxt = URLEncoder.encode("hello", "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("mon12jan", "UTF-8");
        Response act = given().when().get("/api/pat/{txt}", txt);
        act.then().statusCode(200).body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testFpeRecognition() throws Exception {
        String arrangeTxt = URLEncoder.encode("sample", "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("1.23e+45", "UTF-8");
        Response act = given().when().get("/api/pat/{txt}", txt);
        act.then().statusCode(200).body(equalTo("none"));
    }

    @Test(timeout = 60000)
    public void testNoneRecognition() throws Exception {
        String arrangeTxt = URLEncoder.encode("another", "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("foobar", "UTF-8");
        Response act = given().when().get("/api/pat/{txt}", txt);
        act.then().statusCode(200).body(equalTo("none"));
    }
}