package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class DateParseTest {

    @BeforeClass
    public static void setup() {
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
    public void testMonJanProducesTwo() {
        given().when().get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/mon/jan");
        String body = act.getBody().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testNoDayDecProducesTwelve() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/noday/dec");
        String body = act.getBody().asString();
        assertEquals("12", body);
    }

    @Test(timeout = 60000)
    public void testWedAugProducesNine() {
        given().when().get("/api/pat/ABABCABAB/banana").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/wed/aug");
        String body = act.getBody().asString();
        assertEquals("9", body);
    }

    @Test(timeout = 60000)
    public void testThurFebProducesThree() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/thur/feb");
        String body = act.getBody().asString();
        assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void testUnknownMonthAndDayProducesZero() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/xyz/unknown");
        String body = act.getBody().asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testUppercaseMonMarProducesFour() {
        given().when().get("/api/ordered4/zebra/yak/x-ray/wolf").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/dateparse/MON/MAR");
        String body = act.getBody().asString();
        assertEquals("4", body);
    }
}