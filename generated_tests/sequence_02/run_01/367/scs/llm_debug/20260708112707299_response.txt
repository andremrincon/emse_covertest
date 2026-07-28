package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    private static String base() {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        if (b.endsWith("/")) {
            b = b.substring(0, b.length() - 1);
        }
        return b;
    }

    private Response call(String w1, String w2, String w3) {
        return given().when().get(base() + "/api/text2txt/" + w1 + "/" + w2 + "/" + w3);
    }

    @Test(timeout = 60000)
    public void testTwoConvertsToDigitTwo() {
        given().when().get(base() + "/api/text2txt/four/x/x").then().statusCode(lessThan(300));
        given().when().get(base() + "/api/text2txt/you/x/x").then().statusCode(lessThan(300));
        Response r = call("two", "ignore", "ignore");
        Assert.assertEquals("2", r.asString());
    }

    @Test(timeout = 60000)
    public void testFourAndForConvertToDigitFour() {
        given().when().get(base() + "/api/text2txt/and/x/x").then().statusCode(lessThan(300));
        given().when().get(base() + "/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        Response r = call("four", "something", "else");
        Assert.assertEquals("4", r.asString());
    }

    @Test(timeout = 60000)
    public void testYouConvertsToU() {
        given().when().get(base() + "/api/text2txt/two/x/x").then().statusCode(lessThan(300));
        given().when().get(base() + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response r = call("you", "any", "thing");
        Assert.assertEquals("u", r.asString());
    }

    @Test(timeout = 60000)
    public void testAndConvertsToN() {
        given().when().get(base() + "/api/text2txt/you/x/x").then().statusCode(lessThan(300));
        given().when().get(base() + "/api/text2txt/are/x/x").then().statusCode(lessThan(300));
        Response r = call("and", "ignored", "ignored");
        Assert.assertEquals("n", r.asString());
    }

    @Test(timeout = 60000)
    public void testAreConvertsToR() {
        given().when().get(base() + "/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get(base() + "/api/text2txt/four/x/x").then().statusCode(lessThan(300));
        Response r = call("are", "x", "y");
        Assert.assertEquals("r", r.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouAndByTheWayCombinations() {
        given().when().get(base() + "/api/text2txt/are/x/x").then().statusCode(lessThan(300));
        given().when().get(base() + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response r = call("see", "you", "now");
        Assert.assertEquals("cu", r.asString());
    }
}