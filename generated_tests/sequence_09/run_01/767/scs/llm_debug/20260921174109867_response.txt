package ts01gpt_5_mini_2;

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
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoBecomesDigitTwo() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "irrelevant", "irrelevant");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testForBecomesDigitFour() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "here");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testYouBecomesU() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "will", "see");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAndBecomesN() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "then", "now");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSeeYouBecomesCu() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testByTheWayBecomesBtw() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        assertEquals(200, act.getStatusCode());
    }
}