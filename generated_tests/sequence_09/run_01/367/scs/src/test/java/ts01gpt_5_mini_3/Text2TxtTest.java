package ts01gpt_5_mini_3;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void returnsNumericTwoWhenWordIsTwo() {
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "two", "ignored", "ignored");
        assertEquals("2", r.asString());
    }

    @Test(timeout = 60000)
    public void returnsNumericFourWhenWordIsFor() {
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "for", "anything", "here");
        assertEquals("4", r.asString());
    }

    @Test(timeout = 60000)
    public void returnsUWhenWordIsYou() {
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "you", "someone", "else");
        assertEquals("u", r.asString());
    }

    @Test(timeout = 60000)
    public void returnsNWhenWordIsAnd() {
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "two", "b", "c").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "and", "x", "y");
        assertEquals("n", r.asString());
    }

    @Test(timeout = 60000)
    public void returnsRWhenWordIsAre() {
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "for", "a", "b").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "are", "x", "y");
        assertEquals("r", r.asString());
    }

    @Test(timeout = 60000)
    public void returnsBtwWhenPhraseIsByTheWay() {
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        Response r = given().when().get(base + "/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        assertEquals("btw", r.asString());
    }
}