package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotypevar_executesMultipleBranches_i28() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "zzz");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotypevar_executes_i7_and_returns_ok_status() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotypevar_executes_branch_i5_result2() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "zzzz");
        assertEquals("2", resp.getBody().asString());
    }
}