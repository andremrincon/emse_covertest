package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testText2Txt_TwoReturns2() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "test").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "alpha", "beta").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_ForReturns4() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "health", "check", "up").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "here").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_SeeYouReturnsCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "probe", "one", "two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_ByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "ready", "set", "go").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_AreReturnsR() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "pre", "post", "meta").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "we", "there").then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2Txt_NoMatchReturnsEmpty() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "init", "ping", "pong").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "unmatchedword", "second", "third").then().body(equalTo(""));
    }
}