package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

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
    public void testTwoConversion() {
        given().when().get("/api/text2txt/for/any/any").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/a/a");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testForFourConversion() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/a/a");
        Assert.assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouConversion() {
        given().when().get("/api/text2txt/two/a/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        Assert.assertEquals("cu", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayConversion() {
        given().when().get("/api/text2txt/are/x/x").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        Assert.assertEquals("btw", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAreConversion() {
        given().when().get("/api/text2txt/for/a/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/are/x/y");
        Assert.assertEquals("r", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDefaultEmptyConversion() {
        given().when().get("/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/hello/world/there");
        Assert.assertEquals("", resp.getBody().asString());
    }
}