package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoConvertsTo2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "setup", "call").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "ignored", "ignored");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForConvertsTo4() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "setup", "call").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "here");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouConvertsToU() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "setup", "call").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "skip", "skip");
        act.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWordAreConvertsToR() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "setup", "call").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "irrelevant", "irrelevant");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouConvertsToCu() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "setup", "call").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "ignored");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayConvertsToBtw() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uuid, "setup", "call").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        act.then().body(equalTo("btw"));
    }
}