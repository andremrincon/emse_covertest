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
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoConvertsTo2() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "two", "anything", "else").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForConvertsTo4() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "for", "something", "here").then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouConvertsToU() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "you", "will", "see").then().assertThat().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSeeYouConvertsToCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "see", "you", "now").then().assertThat().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayConvertsToBtw() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "by", "the", "way").then().assertThat().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreConvertsToR() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "are", "you", "ok").then().assertThat().body(equalTo("r"));
    }
}