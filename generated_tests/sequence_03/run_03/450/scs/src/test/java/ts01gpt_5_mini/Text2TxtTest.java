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
    public void testTwoProduces2() {
        given().when().get("/api/text2txt/you/anything/else").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/anything/else").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/x/y");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testForProduces4() {
        given().when().get("/api/text2txt/you/foo/bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/foo/bar").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/ignore/ignore");
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouProducesCu() {
        given().when().get("/api/text2txt/are/one/two").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        assertEquals("cu", resp.asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayProducesBtw() {
        given().when().get("/api/text2txt/are/alpha/beta").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAreProducesR() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/are/one/two");
        assertEquals("r", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDefaultProducesEmptyString() {
        given().when().get("/api/text2txt/two/a/b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/a/b").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/hello/world/again");
        assertEquals("", resp.asString());
    }
}