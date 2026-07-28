package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "else");
        String body = resp.getBody().asString();
        Assert.assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testWordFourReturns4() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "ignore", "ignore");
        String body = resp.getBody().asString();
        Assert.assertEquals("4", body);
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y");
        String body = resp.getBody().asString();
        Assert.assertEquals("u", body);
    }

    @Test(timeout = 60000)
    public void testWordAndReturnsN() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "b", "c");
        String body = resp.getBody().asString();
        Assert.assertEquals("n", body);
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "ignored");
        String body = resp.getBody().asString();
        Assert.assertEquals("cu", body);
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        String body = resp.getBody().asString();
        Assert.assertEquals("btw", body);
    }
}