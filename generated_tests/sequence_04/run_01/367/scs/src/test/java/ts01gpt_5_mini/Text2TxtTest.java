package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        BASE = prop != null && !prop.isEmpty() ? prop : (env != null && !env.isEmpty() ? env : "http://localhost:8080");
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testTranslateTwo_returns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "X"+uid, "Y"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "ignored", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "else");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testTranslateFor_returns4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "a"+uid, "b"+uid).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "p", "q").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "here");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testTranslateYou_returnsU() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "one", "two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "x", "y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "ignored", "ignored");
        act.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testTranslateSeeYou_returnsCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "alpha", "beta").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "a", "b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testTranslateByTheWay_returnsBtw() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "b", "c").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        act.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testUnknownWord_returnsEmptyString() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "p", "q").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "p", "q").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "again");
        act.then().body(equalTo(""));
    }
}