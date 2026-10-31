package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testSubstituteTwoReturnsDigitTwo() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/anything/else");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubstituteForReturnsDigitFour() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/sample/word");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubstituteYouReturnsU() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/you/are/here");
        resp.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSeeYouBecomesCu() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        resp.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayBecomesBtw() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        resp.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testNoSubstitutionReturnsEmptyString() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/hello/world/test");
        resp.then().body(equalTo(""));
    }
}