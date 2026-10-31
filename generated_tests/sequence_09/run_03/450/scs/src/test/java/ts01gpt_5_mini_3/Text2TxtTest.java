package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("TEST_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("TEST_BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/ignored/ignored");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testWordFourReturns4() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/four/anything/anything");
        assertEquals("4", act.asString());
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/x/y");
        assertEquals("u", act.asString());
    }

    @Test(timeout = 60000)
    public void testWordAreReturnsR() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/see/you");
        assertEquals("r", act.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        assertEquals("cu", act.asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", act.asString());
    }
}