package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "ignore", "ignore");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get("/api/pat/{txt}", "setup2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "else");
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get("/api/pat/{txt}", "setup3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "x", "y");
        assertEquals("u", resp.asString());
    }

    @Test(timeout = 60000)
    public void testWordAndReturnsN() {
        given().when().get("/api/pat/{txt}", "setup4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "b", "c");
        assertEquals("n", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/{txt}", "setup5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "whatever");
        assertEquals("cu", resp.asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/{txt}", "setup6").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        assertEquals("btw", resp.asString());
    }
}