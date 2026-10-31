package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFraction_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/2.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_Series_Status200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0.1");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_nZero_Status200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/2.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_nTwo_Status200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/2/0.0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Status400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/-1/0.1");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_nZero_Status400() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/0.0");
        assertEquals(400, act.getStatusCode());
    }
}