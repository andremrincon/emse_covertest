package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base.url", "http://localhost:8080");
        }
        BASE = env;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturnsDigitTwo() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/text2txt/two/any/any");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordForReturnsDigitFour() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/text2txt/for/x/y");
        assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/text2txt/you/ignore/ignore");
        assertEquals("u", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordAreReturnsR() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/text2txt/are/x/y");
        assertEquals("r", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/text2txt/see/you/now");
        assertEquals("cu", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/text2txt/by/the/way");
        assertEquals("btw", resp.getBody().asString());
    }
}