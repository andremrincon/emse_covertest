package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testShortPatternDoesNotInvokeReverse_andReturnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("setup short", StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        given().when().get("/api/pat/" + URLEncoder.encode("hello world", StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode("ab", StandardCharsets.UTF_8.name())).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundWithoutReverse_returnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("arrange text", StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        given().when().get("/api/pat/" + URLEncoder.encode("xxABCyy", StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode("ABC", StandardCharsets.UTF_8.name())).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundWithoutPattern_returnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("setup reverse", StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        given().when().get("/api/pat/" + URLEncoder.encode("yyCBAzz", StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode("ABC", StandardCharsets.UTF_8.name())).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBothPatternAndReversePresent_returnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("prepare both", StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        given().when().get("/api/pat/" + URLEncoder.encode("zzABCkkkCBAzz", StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode("ABC", StandardCharsets.UTF_8.name())).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromeAdjacentPattern_returnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("setup palindrome", StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        given().when().get("/api/pat/" + URLEncoder.encode("ABAABA", StandardCharsets.UTF_8.name()) + "/" + URLEncoder.encode("ABA", StandardCharsets.UTF_8.name())).then().statusCode(200);
    }
}