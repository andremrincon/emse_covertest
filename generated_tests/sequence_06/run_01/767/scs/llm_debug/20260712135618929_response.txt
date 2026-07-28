package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "two", "anything", "anything");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get("/api/pat/{txt}", "ping-" + java.util.UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "for", "x", "y");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get("/api/pat/{txt}", "probe").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "you", "any", "any");
        resp.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWordAreReturnsR() {
        given().when().get("/api/pat/{txt}", "setup-" + java.util.UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "are", "you", "ok");
        resp.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "see", "you", "later");
        resp.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "by", "the", "way");
        resp.then().body(equalTo("btw"));
    }
}