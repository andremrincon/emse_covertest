package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {
    static {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/a/b");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/x/" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/sample/val");
        assertEquals("4", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/are/a/b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        assertEquals("cu", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/a/b").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/are/see/you");
        assertEquals("r", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyString() {
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/hello/world/again");
        assertEquals("", resp.getBody().asString());
    }
}