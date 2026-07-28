package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void returns3WhenIEquals28() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void returns3WhenIEquals7TriggersHello7Branch() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "alpha");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void returns2WhenCompareToIsLessAndYNotGreaterThanFive() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 3, "z");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void returns0WhenNoConditionsMet() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "a");
        resp.then().body(equalTo("0"));
    }
}