package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testTwoMapsTo2() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "for", "ignored", id).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "four", "ignored", id).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "you", "ignored", id).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "two", "any", "any");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testForMapsTo4() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "and", "b", id).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "are", "b", id).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "for", "anything", "else");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFourMapsTo4() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "see", "you", id).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "four", "p", "q");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouMapsToCu() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "you", "x", id).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "and", "b", id).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "see", "you", "ignored");
        assertEquals("cu", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayMapsToBtw() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "two", "a", id).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "for", "b", id).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        assertEquals("btw", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyString() {
        String id = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "you", "z", id).then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "and", "z", id).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "hello", "there", "world");
        assertEquals("", act.getBody().asString());
    }
}