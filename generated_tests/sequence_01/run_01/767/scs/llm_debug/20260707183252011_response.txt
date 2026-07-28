package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Ordered4Test {

    private static final String BASE;
    static {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = System.getProperty("baseUrl", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testIncreasingOrdered4() {
        given().baseUri(BASE).when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "banana", "elder", "cherry");
        assertEquals("increasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDecreasingOrdered4() {
        given().baseUri(BASE).when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "elder", "cherry", "apple", "banana");
        assertEquals("decreasing", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToOrder() {
        given().baseUri(BASE).when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "cherry", "banana", "datee");
        assertEquals("unordered", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLength() {
        given().baseUri(BASE).when().get("/api/notypevar/{i}/{s}", "5", "example-string").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/api/ordered4/{w}/{x}/{z}/{y}", "toolong", "apple", "banana", "chess");
        assertEquals("unordered", resp.asString());
    }
}