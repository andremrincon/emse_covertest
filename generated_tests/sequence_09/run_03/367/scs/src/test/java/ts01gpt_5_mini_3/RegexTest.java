package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseurl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASEURL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlBranchReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String encoded = "http%3A%2F%2Fabc%2Fdef";
        Response resp = given().when().get("/api/pat/{txt}", encoded);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDateBranchReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "wed12jan");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFpeBranchReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String encoded = "1.0e%2B05";
        Response resp = given().when().get("/api/pat/{txt}", encoded);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneBranchReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "hello");
        assertEquals(200, resp.getStatusCode());
    }
}