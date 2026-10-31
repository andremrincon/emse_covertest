package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("API_BASE")).orElseGet(() -> System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = Optional.ofNullable(System.getProperty("api.base")).orElseGet(() -> System.getenv("API_BASE_URL"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoProducesDigit2_andArrangeCoversFourBranch() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "four", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "two", "anything", "else");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testYouProducesU_andArrangeCoversAndBranch() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "and", "x", "y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "you", "x", "y");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("u", body);
    }

    @Test(timeout = 60000)
    public void testAreProducesR_andArrangeCoversSeeYouBranch() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "see", "you", "now").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "are", "we", "here");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("r", body);
    }

    @Test(timeout = 60000)
    public void testByTheWayProducesBtw_andArrangeHealthCheck() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "by", "the", "way");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("btw", body);
    }
}