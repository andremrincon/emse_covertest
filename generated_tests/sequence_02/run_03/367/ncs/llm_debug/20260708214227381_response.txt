package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_whenMExceedsLimit_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1001/1/0.75");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_whenInternalThrows_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/8/5/1.2");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_whenValidParameters_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainder_whenValidParameters_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainder_whenAOutOfRange_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/10001/1");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainder_whenBDivisorZero_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/10/0");
        assertEquals(200, resp.getStatusCode());
    }
}