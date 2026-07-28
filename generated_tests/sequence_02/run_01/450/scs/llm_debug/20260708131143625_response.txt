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
    public static void setUp() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "anything");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get("/api/pat/{txt}", "arrange2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "something", "else");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get("/api/pat/{txt}", "arrange3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "ignored", "ignored");
        resp.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/{txt}", "arrange4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now");
        resp.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/{txt}", "arrange5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        resp.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/pat/{txt}", "arrange6").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "ok");
        resp.then().body(equalTo("r"));
    }
}