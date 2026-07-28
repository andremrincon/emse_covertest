package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("TEST_BASE_URL");
        if (base == null) base = System.getenv("TEST_BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("baseUrl");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/anything/anything");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFourReturns4ForFour() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/four/x/y");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsRAndPreventsSeeCu() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/you/now");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testDefaultReturnsEmptyStringWhenNoMatch() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/hello/world/test");
        act.then().body(equalTo(""));
    }
}