package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {
    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("BASE_URL");
        if (cfg == null) cfg = System.getProperty("base.url");
        if (cfg == null) cfg = System.getenv("BASE_URL");
        if (cfg == null) cfg = System.getenv("base.url");
        if (cfg == null) cfg = "http://localhost:8080";
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_finalResult3() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "aaa");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_executesHello7Branch() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i3_compareToBranchSetsTwo() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 3, "world");
        assertEquals("2", resp.getBody().asString());
    }
}