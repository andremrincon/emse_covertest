package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import java.util.UUID;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = when().get("/api/text2txt/{word1}/{word2}/{word3}", "two", "x", "y");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = when().get("/api/text2txt/{word1}/{word2}/{word3}", "for", "anything", "here");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = when().get("/api/text2txt/{word1}/{word2}/{word3}", "you", "a", "b");
        act.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsRAndPreventsSeeYou() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = when().get("/api/text2txt/{word1}/{word2}/{word3}", "are", "see", "you");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = when().get("/api/text2txt/{word1}/{word2}/{word3}", "see", "you", "now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = when().get("/api/text2txt/{word1}/{word2}/{word3}", "by", "the", "way");
        act.then().body(equalTo("btw"));
    }
}