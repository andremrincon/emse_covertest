package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("BASE_URL");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "ignored", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "ignored", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "ignored", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "ignored", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "ignored", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "notyou", "now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "else").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "a", "b").then().statusCode(lessThan(300));
        when().get("/api/text2txt/{w}/{x}/{y}", "for", "something", "else").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "x", "y").then().statusCode(lessThan(300));
        when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "notyou", "x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y").then().statusCode(lessThan(300));
        when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "x", "y").then().statusCode(lessThan(300));
        when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "now").then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmpty() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "a", "b").then().statusCode(lessThan(300));
        when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "again").then().body(equalTo(""));
    }
}