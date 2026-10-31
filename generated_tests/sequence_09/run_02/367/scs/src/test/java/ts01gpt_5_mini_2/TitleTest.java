package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            String ev = System.getenv("BASE_URL");
            RestAssured.baseURI = (ev == null || ev.isEmpty()) ? "http://localhost:8080" : ev;
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testMaleTitleMatchesAcceptedTitle() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleTitleNonMatchingTitleReturnsHandledResponse() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "smith");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMatchesAcceptedTitle() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "ms");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleTitleNonMatchingTitleReturnsHandledResponse() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "doe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneSexMatchesNeutralTitles() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneSexWithNonMatchingTitleHandledGracefully() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "random");
        resp.then().statusCode(200);
    }
}