package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    private static final String BASE;
    static {
        String b = System.getProperty("API_BASE");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testTwoConversion() {
        given().when().get(BASE + "/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/two/x/y").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForConversion() {
        given().when().get(BASE + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/for/anything/else").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouConversion() {
        given().when().get(BASE + "/api/text2txt/are/skip/skip").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/you/abc/def").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAndConversion() {
        given().when().get(BASE + "/api/text2txt/four/sample/one").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/and/x/y").then().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testSeeYouConversion() {
        given().when().get(BASE + "/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/see/you/now").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testDefaultEmptyResult() {
        given().when().get(BASE + "/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/hello/world/there").then().body(equalTo(""));
    }
}