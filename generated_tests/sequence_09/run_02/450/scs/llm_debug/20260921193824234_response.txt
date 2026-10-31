package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

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
    public void testNotypevar_returnsThree_forLargeI() {
        String txt;
        try {
            txt = URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", "28", "example-string");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_returnsTwo_whenCompareToLess() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", "3", "zzz");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_endpoint_accessible_and_triggersConstructor_branch() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", "7", "a");
        resp.then().statusCode(200);
    }
}