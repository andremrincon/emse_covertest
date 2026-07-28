package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("base.url");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "ignored");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "whatever", "x");
        Assert.assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now");
        Assert.assertEquals("cu", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        Assert.assertEquals("btw", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "ok");
        Assert.assertEquals("r", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnknownReturnsEmptyString() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "!");
        Assert.assertEquals("", resp.getBody().asString());
    }
}