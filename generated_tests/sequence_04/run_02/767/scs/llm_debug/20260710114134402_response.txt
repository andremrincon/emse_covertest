package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("apiBaseUrl");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "ignored", "ignored").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "something", "else").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get("/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "any", "any").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "ok").then().body(equalTo("r"));
    }
}