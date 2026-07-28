package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", id).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "else").then().extract().response();
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testForAndFourReturn4() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "ignored", "ignored").then().extract().response();
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().extract().response();
        assertEquals("cu", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().extract().response();
        assertEquals("btw", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAreReturnsRPrefersAreOverSee() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "now").then().extract().response();
        assertEquals("r", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyString() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-"+UUID.randomUUID().toString(), "1", "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "foo").then().extract().response();
        assertEquals("", act.getBody().asString());
    }
}