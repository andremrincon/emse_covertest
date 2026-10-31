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
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }
    @Test(timeout = 60000)
    public void testWordTwoProduces2() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/one/two");
        assertEquals("2", act.getBody().asString());
    }
    @Test(timeout = 60000)
    public void testWordForProduces4() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/quick/brown");
        assertEquals("4", act.getBody().asString());
    }
    @Test(timeout = 60000)
    public void testWordYouProducesU() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/anything/else");
        assertEquals("u", act.getBody().asString());
    }
    @Test(timeout = 60000)
    public void testSeeYouProducesCu() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/today");
        assertEquals("cu", act.getBody().asString());
    }
    @Test(timeout = 60000)
    public void testByTheWayProducesBtw() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", act.getBody().asString());
    }
    @Test(timeout = 60000)
    public void testUnknownWordsProduceEmptyString() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/hello/world/test");
        assertEquals("", act.getBody().asString());
    }
}