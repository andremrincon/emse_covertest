package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReplacedWith2() {
        given().when().get("/api/text2txt/a/b/c").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/anything/else");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFourReplacedWith4() {
        given().when().get("/api/text2txt/a/b/c").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/four/quick/brown");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouGivesCu() {
        given().when().get("/api/text2txt/a/b/c").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        resp.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayGivesBtw() {
        given().when().get("/api/text2txt/a/b/c").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        resp.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testNoMatchReturnsEmptyString() {
        given().when().get("/api/text2txt/a/b/c").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/hello/world/test");
        resp.then().body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testAreReplacedWithR() {
        given().when().get("/api/text2txt/a/b/c").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/are/you/ok");
        resp.then().body(equalTo("r"));
    }
}