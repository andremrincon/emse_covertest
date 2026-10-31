package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoProducesOk() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "irrelevant", "irrelevant").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "irrelevant", "irrelevant").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "else");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordYouProducesOk() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "anything", "else").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "random", "token");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordAreSkipsElseIfsProducesOk() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "ignored").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "something", "else");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouCombinationProducesOk() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "again").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayCombinationProducesOk() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "something", "else", "here").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoMatchProducesOk() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "x", "y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "friend");
        act.then().statusCode(200);
    }

}