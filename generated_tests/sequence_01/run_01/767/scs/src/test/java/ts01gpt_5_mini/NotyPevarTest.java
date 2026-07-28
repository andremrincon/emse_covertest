package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_WhenCompareToIsLess_ShouldReturn2() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 0, "z");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_DefaultPath_ShouldReturn0() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 0, "a");
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_WhenIgt5_ShouldReturn3() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "anything");
        assertEquals("3", act.getBody().asString());
    }
}