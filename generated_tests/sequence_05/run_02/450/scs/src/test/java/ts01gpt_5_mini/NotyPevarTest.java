package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        BASE = System.getProperty("api.base", System.getenv("API_BASE"));
        if (BASE == null || BASE.isEmpty()) {
            BASE = "http://localhost:8080";
        }
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testNotyPevarReturnsZero() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/{i}/{s}", 5, "a");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarReturnsTwo() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/{i}/{s}", 5, "world");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarReturnsThree() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/notypevar/{i}/{s}", 6, "a");
        act.then().body(equalTo("3"));
    }
}