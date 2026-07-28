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
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            String env2 = System.getenv("BASE_URL");
            RestAssured.baseURI = (env2 != null && !env2.isEmpty()) ? env2 : "http://localhost:8080";
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testTwoReturnsNumeric2() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/any/any");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testForReturnsNumeric4() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/anything/else");
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/you/x/y");
        assertEquals("u", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAreReturnsR_preventsSeeYouBranch() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/text2txt/are/" + unique + "/value");
        assertEquals("r", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu_whenSecondIsYou() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        assertEquals("cu", resp.asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", resp.asString());
    }
}