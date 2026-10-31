package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCompareToBranchProducesTwo() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "z");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDefaultReturnStatus200ForZeroInput() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "a");
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testHelloConcatAndYGreaterThanFiveLeadsToThree() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals("3", resp.asString());
    }
}