package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnsZeroForSmallIAndNonMatchingS() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 1, "a");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReturnsTwoWhenIIsFiveAndSIsGreaterThanHello() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "z");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReturnsThreeWhenITriggersOverwriteByYGreaterThanFive() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        resp.then().body(equalTo("3"));
    }
}