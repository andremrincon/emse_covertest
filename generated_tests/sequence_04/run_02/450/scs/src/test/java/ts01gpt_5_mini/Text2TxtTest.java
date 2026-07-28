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
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/Two/anything/else");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testFourReturns4() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/four/ignore/ignore");
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        assertEquals("cu", resp.asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/ARE/you/way");
        assertEquals("r", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoMatchReturnsEmpty() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/hello/world/x");
        assertEquals("", resp.asString());
    }
}