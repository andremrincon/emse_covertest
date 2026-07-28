package ts01gpt_5_mini;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testConvertTwoToDigit() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "ping-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "Two", "ignore", "ignore").andReturn();
        Assert.assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testConvertForToFourDigit() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "ping-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "For", "something", "else").andReturn();
        Assert.assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testConvertYouToU() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "ping-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "You", "ignored", "ignored").andReturn();
        Assert.assertEquals("u", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouToCu() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "ping-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "See", "You", "later").andReturn();
        Assert.assertEquals("cu", resp.asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayToBtw() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "ping-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").andReturn();
        Assert.assertEquals("btw", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyString() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "ping-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "again").andReturn();
        Assert.assertEquals("", resp.asString());
    }
}