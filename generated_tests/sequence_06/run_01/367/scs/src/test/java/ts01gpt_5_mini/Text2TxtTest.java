package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testTwoReplacedWith2() {
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/any/any");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForOrFourReplacedWith4() {
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/a/b");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouCombinedCU() {
        given().when().get("/api/text2txt/are/foo/bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/example/one").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/whatever");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayBTW() {
        given().when().get("/api/text2txt/see/you/zzz").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/see/you").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreMapsToRAndPreventsSeeElseIf() {
        given().when().get("/api/text2txt/for/alpha/beta").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/alpha").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/see/you");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testDefaultEmptyWhenNoMatch() {
        given().when().get("/api/text2txt/you/you/you").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/and/and").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/hello/world/again");
        act.then().body(equalTo(""));
    }
}