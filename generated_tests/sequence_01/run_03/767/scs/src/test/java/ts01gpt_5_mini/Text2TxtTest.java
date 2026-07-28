package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "two", "x", "y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "two", "x", "y");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForUppercaseReturns4() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "FOR", "anything", "else").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "FOR", "anything", "else");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "see", "you", "now").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "see", "you", "now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "By", "the", "way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "By", "the", "way");
        act.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreTakesPrecedenceReturnsR() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "are", "you", "way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "are", "you", "way");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testNoMatchReturnsEmpty() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "hello", "world", "!").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "hello", "world", "!");
        act.then().body(equalTo(""));
    }
}