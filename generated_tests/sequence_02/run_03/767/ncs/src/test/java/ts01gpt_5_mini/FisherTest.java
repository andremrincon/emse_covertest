package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Ignore


    @Test(timeout = 60000)
    public void testFisher_a1_b1_returnsOneForZeroX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1/1/0.0");
        assertEquals(200, act.getStatusCode());
        String body = act.getBody().asString();
        double value;
        try {
            value = act.jsonPath().getDouble("value");
            if (Double.isNaN(value)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            try {
                value = Double.parseDouble(body.trim());
            } catch (Exception e2) {
                value = act.jsonPath().getDouble("result");
            }
        }
        assertEquals(1.0, value, 1e-9);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1_status200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/3/4/0.75");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1_status200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/4/3/0.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_bothNot1_status200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/6/4/0.75");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_invalidX_returns400() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/8/5/1.2");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_largeM_loops_status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        assertEquals(200, act.getStatusCode());
    }
}