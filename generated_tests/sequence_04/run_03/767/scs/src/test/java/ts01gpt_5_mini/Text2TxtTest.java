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
        String base = System.getProperty("api.base.url");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoMapping() {
        given().when().get("/api/text2txt/four/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/a/b");
        String body = act.getBody().asString();
        Assert.assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testYouMapping() {
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/foo/bar");
        String body = act.getBody().asString();
        Assert.assertEquals("u", body);
    }

    @Test(timeout = 60000)
    public void testAndMapping() {
        given().when().get("/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/and/ignore/ignore");
        String body = act.getBody().asString();
        Assert.assertEquals("n", body);
    }

    @Test(timeout = 60000)
    public void testAreMapping() {
        given().when().get("/api/text2txt/see/you/z").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/any/thing");
        String body = act.getBody().asString();
        Assert.assertEquals("r", body);
    }

    @Test(timeout = 60000)
    public void testSeeYouMapping() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        String body = act.getBody().asString();
        Assert.assertEquals("cu", body);
    }

    @Test(timeout = 60000)
    public void testByTheWayMapping() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        String body = act.getBody().asString();
        Assert.assertEquals("btw", body);
    }
}