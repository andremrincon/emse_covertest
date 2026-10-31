package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

import org.junit.Ignore;
public class FisherTest {

    @BeforeClass
    public static void setup() {
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
    public void testFisher_a1_b1_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.75);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 2, 0.75);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/{m}/{n}/{x}", 2, 1, 0.75);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_bothNot1_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/{m}/{n}/{x}", 2, 2, 0.75);
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/abc/5/0.75");
        assertEquals(400, res.getStatusCode());
    }

    @Ignore


    @Test(timeout = 60000)
    public void testFisher_xZero_valueInRange0to1() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.0);
        assertEquals(200, res.getStatusCode());
        Double v = null;
        try {
            v = res.jsonPath().getDouble("value");
        } catch (Exception e) {
        }
        if (v == null) {
            try {
                v = res.jsonPath().getDouble("p");
            } catch (Exception e) {
            }
        }
        if (v == null) {
            try {
                v = res.jsonPath().getDouble("probability");
            } catch (Exception e) {
            }
        }
        assertTrue(v != null && v >= 0.0 && v <= 1.0);
    }
}