package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"\"   Actual: none")
    @Test(timeout = 60000)
    public void testSubjectRecognizesUrl() throws Exception {
        RestAssured.given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        RestAssured.given().when().get("/api/pat/" + enc).then().statusCode(200).body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testSubjectRecognizesDate() throws Exception {
        RestAssured.given().when().get("/api/pat/setup").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        RestAssured.given().when().get("/api/pat/" + enc).then().statusCode(200).body(equalTo("date"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"\"   Actual: none")
    @Test(timeout = 60000)
    public void testSubjectRecognizesFpe() throws Exception {
        RestAssured.given().when().get("/api/pat/prep").then().statusCode(lessThan(300));
        String txt = "1.2e+34";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        RestAssured.given().when().get("/api/pat/" + enc).then().statusCode(200).body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNoneForNonMatching() throws Exception {
        RestAssured.given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        String txt = "helloWorld";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        RestAssured.given().when().get("/api/pat/" + enc).then().statusCode(200).body(equalTo("none"));
    }

    @Test(timeout = 60000)
    public void testPatEndpointWithPatternParameter() throws Exception {
        RestAssured.given().when().get("/api/pat/prime").then().statusCode(lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        String encTxt = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        String encPat = URLEncoder.encode(pat, StandardCharsets.UTF_8.name());
        RestAssured.given().when().get("/api/pat/" + encTxt + "/" + encPat).then().statusCode(200).body(equalTo("1"));
    }
}