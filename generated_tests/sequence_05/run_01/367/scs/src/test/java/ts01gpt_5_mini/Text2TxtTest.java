package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setupClass() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", System.getenv("api.base"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "by", "the", "way");
        act.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "we", "ok");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testTwoUppercaseReturns2() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "Two", "anything", "else");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFourReturns4() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "x", "y");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "ignored", "ignored");
        act.then().body(equalTo("u"));
    }
}