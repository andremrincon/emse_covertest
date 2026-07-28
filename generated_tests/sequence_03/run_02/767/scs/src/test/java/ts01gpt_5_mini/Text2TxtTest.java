package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String cfg = System.getProperty("API_BASE", System.getenv("API_BASE"));
        base = (cfg == null || cfg.isEmpty()) ? "http://localhost:8080" : cfg;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get(base + "/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "two", "ignored", "ignored")
                .then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get(base + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "for", "x", "y")
                .then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get(base + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "you", "any", "any")
                .then().assertThat().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWordAndReturnsN() {
        given().when().get(base + "/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "and", "b", "c")
                .then().assertThat().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testWordArePreventsSeeYouAndReturnsR() {
        given().when().get(base + "/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "are", "see", "you")
                .then().assertThat().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get(base + "/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "by", "the", "way")
                .then().assertThat().body(equalTo("btw"));
    }
}