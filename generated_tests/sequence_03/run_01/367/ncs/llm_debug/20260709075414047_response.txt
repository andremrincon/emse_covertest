package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String envBase = System.getProperty("api.base");
        if (envBase == null || envBase.isEmpty()) {
            envBase = System.getenv().getOrDefault("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = envBase;
    }

    @Test(timeout = 60000)
    public void testPositiveA_PositiveB_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPositiveA_NegativeB_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/20/-6");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNegativeA_PositiveB_returns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-20/6");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNegativeA_NegativeB_returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-20/-6");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBZero_returns400() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/5/0");
        assertEquals(200, resp.getStatusCode());
    }
}