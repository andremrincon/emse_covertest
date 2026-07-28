package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import org.junit.Assert;

public class Text2TxtTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        BASE = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/text2txt/{word1}/{word2}/{word3}", "two", "x", "y");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/text2txt/{word1}/{word2}/{word3}", "for", "anything", "else");
        Assert.assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/text2txt/{word1}/{word2}/{word3}", "you", "b", "c");
        Assert.assertEquals("u", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordAndReturnsN() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/text2txt/{word1}/{word2}/{word3}", "and", "b", "c");
        Assert.assertEquals("n", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/text2txt/{word1}/{word2}/{word3}", "see", "you", "ignored");
        Assert.assertEquals("cu", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/text2txt/{word1}/{word2}/{word3}", "by", "the", "way");
        Assert.assertEquals("btw", resp.getBody().asString());
    }
}