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

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("baseUrl");
        BASE = env != null ? env : (prop != null ? prop : "http://localhost:8080");
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyString() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "alpha" + uuid, "beta", "gamma").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "noneMatch", "nothing", "here");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("", body);
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "health", "check", "ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "Two", "ignored", "ignored");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testWordFourReturns4() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "ping", "pong", "test").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "For", "x", "y");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("4", body);
    }

    @Test(timeout = 60000)
    public void testAreTakesPrecedenceOverSeeYou() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "pre", "setup", "ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "ARE", "you", "whatever");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("r", body);
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "ready", "set", "go").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "See", "You", "now");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("cu", body);
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "init", "start", "ok").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "By", "The", "Way");
        String body = resp.getBody().asString().replace("\"", "").trim();
        assertEquals("btw", body);
    }
}